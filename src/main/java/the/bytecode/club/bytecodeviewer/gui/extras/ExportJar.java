package the.bytecode.club.bytecodeviewer.gui.extras;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import the.bytecode.club.bytecodeviewer.BytecodeViewer;
import the.bytecode.club.bytecodeviewer.util.JarUtils;

/***************************************************************************
 * Bytecode Viewer (BCV) - Java & Android Reverse Engineering Suite        *
 * Copyright (C) 2014 Kalen 'Konloch' Kinloch - http://bytecodeviewer.com  *
 *                                                                         *
 * This program is free software: you can redistribute it and/or modify    *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation, either version 3 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 *   This program is distributed in the hope that it will be useful,       *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the         *
 *   GNU General Public License for more details.                          *
 *                                                                         *
 *   You should have received a copy of the GNU General Public License     *
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>. *
 ***************************************************************************/

/**
 * The export as Jar UI.
 *
 * @author Konloch
 */

public class ExportJar extends JFrame {
    public ExportJar(final String jarPath) {
        setSize(new Dimension(250, 277));
        setResizable(false);
        setTitle("Save As Jar..");

        JButton btnNewButton = new JButton("Save As Jar..");
        btnNewButton.setMaximumSize(new Dimension(999, 23));
        btnNewButton.setMinimumSize(new Dimension(999, 23));
        btnNewButton.setSize(new Dimension(999, 0));
        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane);

        JLabel lblMetainfmanifestmf = new JLabel("META-INF/MANIFEST.MF:");
        scrollPane.setColumnHeaderView(lblMetainfmanifestmf);

        final JTextArea mani = new JTextArea();
        mani.setText("Manifest-Version: 1.0\r\nClass-Path: .\r\nMain-Class: ");
        scrollPane.setViewportView(mani);
        getContentPane().add(btnNewButton);

        btnNewButton.addActionListener(arg0 -> {
            BytecodeViewer.viewer.updateBusyStatus(true);
            Thread t = new Thread(() -> {
                JarUtils.saveAsJar(BytecodeViewer.getLoadedClasses(), jarPath,
                        mani.getText());
                BytecodeViewer.viewer.updateBusyStatus(false);
            });
            t.start();
            dispose();
        });

        this.setLocationRelativeTo(null);
    }

    private static final long serialVersionUID = -2662514582647810868L;
}