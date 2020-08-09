package Components.Middle;

import java.io.IOException;

import Framework.GeneralFilter;

/**
 * @author Jungho Kim
 * @date 2019
 * @version 1.1
 * @description
 */
public class MiddleFilter_2 extends GeneralFilter {

	public MiddleFilter_2(int totalNoOfOutputPort, int totalNoOfInputPort) {
		super(totalNoOfOutputPort, totalNoOfInputPort);

	}

	@Override
	public void setFilePath(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void specificComputationForFilter() throws IOException {
		int checkBlank = 4;
		int numOfBlank = 0;
		int idx = 0;
		byte[] buffer = new byte[64];
		boolean isCS = false;

		int byte_read = 0;

		while (true) {

			// check "CS" on byte_read from student information
			while (byte_read != '\n' && byte_read != -1) {
				
				byte_read = in.get(1).read();
				

				if (byte_read == ' ') {
					numOfBlank++;
				}
				if (byte_read != -1) {
					buffer[idx++] = (byte) byte_read;
				}

			}

			for (int i = 0; i < idx; i++) {
				
				out.get(1).write((char) buffer[i]);
				// System.out.print((char)buffer[i]);
			}

			if (byte_read == -1)
				return;

			idx = 0;
			numOfBlank = 0;
			byte_read = '\0';
		}
	}

}
