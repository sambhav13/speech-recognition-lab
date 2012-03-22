package speech.recognition;
import java.awt.Component;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

import javax.swing.JFileChooser;

public class Voice {

	private JFileChooser fc = new JFileChooser(
			"D:/Study/Univer/Kurs3/Voice/samples");

	private short[] data;

	public short[] load(Component window) throws IOException {
		int returnVal = fc.showOpenDialog(window);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			ReadableByteChannel fis = new FileInputStream(fc.getSelectedFile())
					.getChannel();

			ByteBuffer buffer = ByteBuffer.allocateDirect((int) fc
					.getSelectedFile().length());

			int numRead = 0;
			while (numRead >= 0) {
				buffer.rewind();
				numRead = fis.read(buffer);
				buffer.rewind();

				for (int i = 0; i < numRead; i++) {
					byte b = buffer.get();
					System.out.println(b);
				}
			}

			int length = (buffer.limit() - 88) / 2;
			System.out.println(buffer.limit());
			System.out.println(length);
			data = new short[length];

			for (int i = 87; i < buffer.limit() - 1; i += 2) {
				data[(i - 87) / 2] = buffer.getShort(i);
				System.out.println(data[(i - 87) / 2]);
			}

			return data;
		}
		return null;

	}

}
