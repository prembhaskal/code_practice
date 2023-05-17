import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: premkumarbhaskal
 * Date: 1/12/12
 * Time: 1:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestDialogClass {

	public static void main(String[] a) {
		JDialog dialog = new JDialog((Dialog)null, true);
		dialog.setSize(400, 400);
//		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JTextField textField = new JTextField(10);

		dialog.add(textField);
		dialog.setVisible(true);
	}
}
