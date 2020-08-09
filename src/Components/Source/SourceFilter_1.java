package Components.Source;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Framework.GeneralFilter;

/**
 * @author Jungho Kim
 * @date 2019
 * @version 1.1
 * @description
 */
public class SourceFilter_1 extends GeneralFilter {
	private String filePath;

	public SourceFilter_1(int totalNoOfOutputPort, int totalNoOfInputPort) {
		super(totalNoOfOutputPort, totalNoOfInputPort);
 
	}
	@Override
	public void setFilePath(String inputFilePath) {
		this.filePath = inputFilePath;
		
	}


	@Override
	public void specificComputationForFilter() throws IOException {
		int byte_read;
		try {
			@SuppressWarnings("resource")
			BufferedInputStream br = new BufferedInputStream(new FileInputStream(new File(filePath)));
			for (;;) {
				byte_read = br.read();
				if (byte_read == -1) {
					return;
				}
				out.get(0).write(byte_read);
			}
		} catch (IOException e) {
			if (e instanceof EOFException)
				return;
			else
				System.out.println(e);
		} finally {
			try {
				out.get(0).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



	
}
