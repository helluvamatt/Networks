package com.schneenet.networks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private GamePanel panel;
	private int difficulty_h;
	private int difficulty_w;
	private boolean use_routers;
	
	public MainWindow() {
		
		// Content pane
		panel = new GamePanel();
		panel.addMouseListener(panel);
		panel.addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {
				pack();
			}
			// Don't care
			public void componentShown(ComponentEvent e) {}
			public void componentHidden(ComponentEvent e) {}
			public void componentMoved(ComponentEvent e) {}
		});
		
		// Set up window
		this.setTitle("Networks");
		this.add(panel);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				panel.closing();
				System.exit(0);
			}
		});
		
		// Prepare Menus
		JMenuBar menubar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenuItem newGameItem = new JMenuItem("New Game");
		newGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		newGameItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.newGame();
			}
		});
		gameMenu.add(newGameItem);
		gameMenu.add(new JSeparator());
		JMenu difficultyMenu = new JMenu("Difficulty");
		ActionListener sizeListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				difficulty_w = Integer.parseInt(cmd.substring(0, cmd.indexOf("x")));
				difficulty_h = Integer.parseInt(cmd.substring(cmd.indexOf("x")+1));
				panel.setDifficulty(difficulty_w, difficulty_h, use_routers);
			}
		};
		JMenuItem sizeEasyMenuItem = new JMenuItem("Easy (5 x 5)");
		sizeEasyMenuItem.setActionCommand("5x5");
		sizeEasyMenuItem.addActionListener(sizeListener);
		difficultyMenu.add(sizeEasyMenuItem);
		JMenuItem sizeMediumMenuItem = new JMenuItem("Medium (7 x 7)");
		sizeMediumMenuItem.setActionCommand("7x7");
		sizeMediumMenuItem.addActionListener(sizeListener);
		difficultyMenu.add(sizeMediumMenuItem);
		JMenuItem sizeHardMenuItem = new JMenuItem("Hard (9 x 9)");
		sizeHardMenuItem.setActionCommand("9x9");
		sizeHardMenuItem.addActionListener(sizeListener);
		difficultyMenu.add(sizeHardMenuItem);
		JMenuItem sizeExpertMenuItem = new JMenuItem("Expert (11 x 11)");
		sizeExpertMenuItem.setActionCommand("11x11");
		sizeExpertMenuItem.addActionListener(sizeListener);
		difficultyMenu.add(sizeExpertMenuItem);
		JMenuItem sizeCustomMenuItem = new JMenuItem("Custom...");
		sizeCustomMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Custom size dialog and updating on OK
				final JTextField widthField = new JTextField(4);				
				final JTextField heightField = new JTextField(4);
				final JComponent[] inputs = new JComponent[] {
					new JLabel("Columns (3 - 15): "),
					widthField,
					new JLabel("Rows (3 - 15): "),
					heightField
				};
				int result = JOptionPane.showConfirmDialog(MainWindow.this, inputs, "Custom Size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					difficulty_w = Integer.parseInt(widthField.getText());
					if (difficulty_w < 3) difficulty_w = 3;
					if (difficulty_w > 15) difficulty_w = 15;
					difficulty_h = Integer.parseInt(heightField.getText());
					if (difficulty_h < 3) difficulty_h = 3;
					if (difficulty_h > 15) difficulty_h = 15;
					panel.setDifficulty(difficulty_w, difficulty_h, use_routers);
				}
			}
		});
		sizeEasyMenuItem.setSelected(true);
		difficultyMenu.add(sizeCustomMenuItem);
		difficultyMenu.add(new JSeparator());
		final JCheckBoxMenuItem useRoutersMenuItem = new JCheckBoxMenuItem("Use Routers");
		useRoutersMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				use_routers = useRoutersMenuItem.isSelected();
				panel.setDifficulty(difficulty_w, difficulty_h, use_routers);
			}
		});
		difficultyMenu.add(useRoutersMenuItem);
		gameMenu.add(difficultyMenu);
		gameMenu.add(new JSeparator());
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.closing();
				MainWindow.this.dispose();
			}
		});
		gameMenu.add(quitItem);
		menubar.add(gameMenu);
		this.setJMenuBar(menubar);
		
		// Layout Components
		this.pack();
	}
	
}
