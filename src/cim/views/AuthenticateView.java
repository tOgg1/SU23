package cim.views;

import cim.models.Account;
import cim.net.Client;
import cim.net.packet.Request;
import cim.net.packet.Response;
import cim.util.CloakedIronManException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticateView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private Account account = null;
	private final Client client;
	

	/**
	 * Create the dialog.
	 */
	public AuthenticateView(Client client) {
		this.client = client;
        this.setResizable(false);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Cloaked Ironman - Logg inn");
		setBounds(100, 100, 315, 130);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("E-post:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtEmail = new JTextField();
			GridBagConstraints gbc_txtEmail = new GridBagConstraints();
			gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
			gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEmail.gridx = 1;
			gbc_txtEmail.gridy = 0;
			contentPanel.add(txtEmail, gbc_txtEmail);
			txtEmail.setColumns(10);
		}
		{
			JLabel lblPassord = new JLabel("Passord:");
			GridBagConstraints gbc_lblPassord = new GridBagConstraints();
			gbc_lblPassord.anchor = GridBagConstraints.EAST;
			gbc_lblPassord.insets = new Insets(0, 0, 0, 5);
			gbc_lblPassord.gridx = 0;
			gbc_lblPassord.gridy = 1;
			contentPanel.add(lblPassord, gbc_lblPassord);
		}
		{
			txtPassword = new JPasswordField();
			GridBagConstraints gbc_txtPassword = new GridBagConstraints();
			gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPassword.gridx = 1;
			gbc_txtPassword.gridy = 1;
			contentPanel.add(txtPassword, gbc_txtPassword);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String email = txtEmail.getText();
						String pw = new String(txtPassword.getPassword());
						txtPassword.setText("");
						try {
							Request req = new Request("AUTHENTICATE", email, pw);
							Response resp = AuthenticateView.this.client.request(req);
							Object[] dataFromResponse = resp.getData();
							Account acc = (Account)dataFromResponse[0];
							AuthenticateView.this.account = acc;
						} catch (CloakedIronManException e1) {
							AuthenticateView.this.client.d(e1.getMessage());
						}
						if (AuthenticateView.this.account != null) {
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(AuthenticateView.this,
								    "Innloggingsinformasjonen stemte ikke.",
								    "Feil",
								    JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Avslutt");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public Account getAccount() {
		return this.account;
	}

}
