package shells.plugins.beichendream;

import core.ui.MainActivity;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FirstGodzillaPluginForMainActivity {
    static {
        JMenu menu = new JMenu("我是单独的主选项");
        menu.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainActivity.getMainActivityFrame(), "我是单独的主选项 click");
            }
        });

        JMenuItem pluginMenuItem=new JMenuItem("我是插件选项下面的选项");
        pluginMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainActivity.getMainActivityFrame(), "我是插件选项下面的选项 click");
            }
        });

        JMenuItem shellViewMenuItem = new JMenuItem("我是shell管理页面的右键菜单");
        shellViewMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainActivity.getMainActivityFrame(), "我是shell管理页面的右键菜单 click");
            }
        });

        MainActivity.registerJMenu(menu);//注册一个单独的菜单在主页面
        MainActivity.registerPluginJMenuItem(pluginMenuItem);//注册一个菜单元素在插件菜单栏下
        MainActivity.registerShellViewJMenuItem(shellViewMenuItem);//注册一个菜单元素在Shell管理主页面的右击弹出菜单中
    }
}
