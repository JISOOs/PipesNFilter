package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Vector;

/**
 * @author Jungho Kim
 * @date 2019
 * @version 1.1
 * @description
 */
public abstract class GeneralFilter implements CommonForFilter {
	protected Vector<PipedInputStream> in = new Vector<PipedInputStream>();
	protected Vector<PipedOutputStream> out = new Vector<PipedOutputStream>();
	
	public GeneralFilter(int totalNoOfOutputPort, int totalNoOfInputPort) {
		for (int i = 0; i < totalNoOfInputPort; i++) {
			in.add(i, new PipedInputStream());
		}
		for (int i = 0; i < totalNoOfOutputPort; i++) {
			out.add(i, new PipedOutputStream());
		}
		
	}

	/**********
	 * Implementation of public methods defined in CommonForFilter interface
	 ************/

	public void connectOutputTo(int noOfOutputPort, CommonForFilter nextFilter, int noOfInputPort)
			throws IOException {
		out.get(noOfOutputPort).connect(nextFilter.getPipedInputStream().get(noOfInputPort));
	}

	public void connectInputTo(int noOfInputPort, CommonForFilter previousFilter, int noOfOutputPort)
			throws IOException {
		in.get(noOfInputPort).connect(previousFilter.getPipedOutputStream().get(noOfOutputPort));
	}

	public Vector<PipedInputStream> getPipedInputStream() {
		return in;
	}

	public Vector<PipedOutputStream> getPipedOutputStream() {
		return out;
	}

	/********** 
	 * Implementation of public methods defined in Runnable interface
	 ************/

	public void run() {
		try {
			specificComputationForFilter();
		} catch (IOException e) {
			if (e instanceof EOFException)
				return;
			else
				System.out.println(e);
		} finally {
			   closePorts();

		}
	}

	/********** Implementation of protected methods ************/

	/**
	 * Filter가 작동을 정지하기 전에 Input/Output Stream port를 닫는다.
	 */
	protected void closePorts() {
		try {
			for (int i = 0; i < in.size(); i++) {
				in.get(i).close();
			}
			for (int i = 0; i < out.size(); i++) {
				out.get(i).close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/********** Abstract method that should be implemented ************/

	/**
	 * 각 Filter의 특수한 기능이 이곳에 기록되며, 이 메소드는 run()에 의해 호출됨으로써 fiter가 가능하게 한다.
	 * 
	 * @throws IOException
	 */
	abstract public void specificComputationForFilter() throws IOException;
}
