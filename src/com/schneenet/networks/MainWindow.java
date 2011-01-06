package com.schneenet.networks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private GamePanel panel;
	private JRadioButtonMenuItem sizeEasyMenuItem;
	private JRadioButtonMenuItem sizeMediumMenuItem;
	private JRadioButtonMenuItem sizeHardMenuItem;
	private JRadioButtonMenuItem sizeExpertMenuItem;
	private JRadioButtonMenuItem sizeCustomMenuItem;
	private ButtonGroup sizeGroup;
	private int difficulty_h;
	private int difficulty_w;
	private boolean use_routers;
	
	public MainWindow() {
		
		// Content pane
		panel = new GamePanel();
		panel.addMouseListener(panel);
		
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
		sizeGroup = new ButtonGroup();
		JMenu difficultyMenu = new JMenu("Difficulty");
		ActionListener sizeListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				difficulty_w = Integer.parseInt(cmd.substring(0, cmd.indexOf("x")));
				difficulty_h = Integer.parseInt(cmd.substring(cmd.indexOf("x")+1));
				panel.setDifficulty(difficulty_w, difficulty_h, use_routers);
			}
		};
		sizeEasyMenuItem = new JRadioButtonMenuItem("Easy (5 x 5)");
		sizeEasyMenuItem.setActionCommand("5x5");
		sizeEasyMenuItem.addActionListener(sizeListener);
		sizeGroup.add(sizeEasyMenuItem);
		difficultyMenu.add(sizeEasyMenuItem);
		sizeMediumMenuItem = new JRadioButtonMenuItem("Medium (7 x 7)");
		sizeMediumMenuItem.setActionCommand("7x7");
		sizeMediumMenuItem.addActionListener(sizeListener);
		sizeGroup.add(sizeMediumMenuItem);
		difficultyMenu.add(sizeMediumMenuItem);
		sizeHardMenuItem = new JRadioButtonMenuItem("Hard (9 x 9)");
		sizeHardMenuItem.setActionCommand("9x9");
		sizeHardMenuItem.addActionListener(sizeListener);
		sizeGroup.add(sizeHardMenuItem);
		difficultyMenu.add(sizeHardMenuItem);
		sizeExpertMenuItem = new JRadioButtonMenuItem("Expert (11 x 11)");
		sizeExpertMenuItem.setActionCommand("11x11");
		sizeExpertMenuItem.addActionListener(sizeListener);
		sizeGroup.add(sizeExpertMenuItem);
		difficultyMenu.add(sizeExpertMenuItem);
		sizeCustomMenuItem = new JRadioButtonMenuItem("Custom...");
		sizeCustomMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO Custom size dialog and updating on OK
			}
		});
		sizeGroup.add(sizeCustomMenuItem);
		difficultyMenu.add(sizeCustomMenuItem);
		difficultyMenu.add(new JSeparator());
		final JCheckBoxMenuItem useRoutersMenuItem = new JCheckBoxMenuItem("Use Routers");
		useRoutersMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				use_routers = useRoutersMenuItem.isSelected();
				panel.setDifficulty(difficulty_w, difficulty_h, use_routers);
			}
		});
		sizeGroup.getSelection();
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
