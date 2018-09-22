 
package com.example.e4.rcp.webbrowser.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class ExitHandler {
	@Execute
	public void execute(IWorkbench workbench, Shell parent) {
		MessageBox dialog = new MessageBox(parent, SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL);
		dialog.setText("exit warning");
		dialog.setMessage("Do you really want to exit?");
		
		int ret = dialog.open();
		if (ret == SWT.OK) {
			workbench.close();
		}
	}		
}