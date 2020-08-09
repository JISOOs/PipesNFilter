package Framework;

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
public interface CommonForFilter extends Runnable{
 
    public void connectOutputTo(int NoOfOutputPort, CommonForFilter filter, int NoOfInputPort) throws IOException;
    
    public void connectInputTo(int NoOfInputPort, CommonForFilter filter, int NoOfOutputPort) throws IOException;
    
    public Vector<PipedInputStream> getPipedInputStream();
    
    public Vector<PipedOutputStream> getPipedOutputStream();

	public void setFilePath(String inputFilePath);

}
