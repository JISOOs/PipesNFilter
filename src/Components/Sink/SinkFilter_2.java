package Components.Sink;

import java.io.FileWriter;
import java.io.IOException;

import Framework.GeneralFilter;

/**
 * @author Jungho Kim
 * @date 2019
 * @version 1.1
 * @description
 */
public class SinkFilter_2 extends GeneralFilter {
	private String filePath;

	public SinkFilter_2(int totalNoOfOutputPort, int totalNoOfInputPort) {
		super(totalNoOfOutputPort, totalNoOfInputPort);
 
	}
	@Override
	public void setFilePath(String outputFilePath) {
		this.filePath = outputFilePath;
		
	}


	@Override
	public void specificComputationForFilter() throws IOException {

		int byte_read;

		FileWriter fw = new FileWriter(this.filePath);

		while (true) {
			byte_read = in.get(1).read();
			if (byte_read == -1) {
				fw.close();
				System.out.print("::Filtering is finished; Output file is created.");
				return;
			}
			fw.write((char) byte_read);

		}
	}





}
