package Framework;

import Components.Middle.MiddleFilter_1;
import Components.Middle.MiddleFilter_2;
import Components.Sink.SinkFilter_1;
import Components.Sink.SinkFilter_2;
import Components.Source.SourceFilter_1;
import Components.Source.SourceFilter_2;

/**
 * @author Jungho Kim
 * @date 2019
 * @version 1.1
 * @description
 * 
 */
public class PipesNFiltersMain {

	public static void main(String[] args) {
		try {
			CommonForFilter filter1 = new SourceFilter_1(2, 2);
			CommonForFilter filter2 = new SourceFilter_2(2, 2);
			CommonForFilter filter3 = new SinkFilter_1(2, 2);
			CommonForFilter filter4 = new SinkFilter_2(2, 2);
			CommonForFilter filter5 = new MiddleFilter_1(2, 2);
			CommonForFilter filter6 = new MiddleFilter_2(2, 2);

			filter1.setFilePath("Students.txt");
			filter2.setFilePath("Courses.txt");
			filter3.setFilePath("Output_1.txt");
			filter4.setFilePath("Output_2.txt");

			filter1.connectOutputTo(0, filter5, 0);
			filter3.connectInputTo(0, filter5, 0);

			filter2.connectOutputTo(1, filter6, 1);
			filter4.connectInputTo(1, filter6, 1);

			Thread thread1 = new Thread(filter1);
			Thread thread2 = new Thread(filter2);
			Thread thread3 = new Thread(filter3);
			Thread thread4 = new Thread(filter4);
			Thread thread5 = new Thread(filter5);
			Thread thread6 = new Thread(filter6);

			thread1.start();
			thread2.start();
			thread3.start();
			thread4.start();
			thread5.start();
			thread6.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}