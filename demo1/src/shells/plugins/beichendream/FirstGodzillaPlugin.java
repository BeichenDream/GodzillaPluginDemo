package shells.plugins.beichendream;

import java.awt.*;
import java.awt.event.*;

import core.ApplicationContext;
import core.Db;
import core.annotation.PluginnAnnotation;
import core.imp.Payload;
import core.shell.ShellEntity;
import core.ui.MainActivity;
import util.Log;

import javax.swing.*;

@PluginnAnnotation(payloadName = "PhpDynamicPayload",Name="FirstGodzillaPlugin") //有效载荷的名字以及插件名  (插件名唯一不可重复)
public class FirstGodzillaPlugin implements core.imp.Plugin{
    private JPanel contentPanel;// 组件内容容器
    private JLabel currentGodzillaVersionLabel;//当前哥斯拉版本
    private JLabel currentDirLabel;//当前web路径
    private JLabel currentUsernameLabel;//当前用户名
    private JButton insertShellButton;//添加一条哥斯拉shell的按钮
    private JButton copyShellButton;//克隆当前的shell



    private ShellEntity shellEntity;
    private Payload payload;


    public FirstGodzillaPlugin(){
        contentPanel=new JPanel();//创建组件内容容器

        currentGodzillaVersionLabel=new JLabel();//创建标签
        currentDirLabel=new JLabel();//创建标签
        currentUsernameLabel=new JLabel();//创建标签
        insertShellButton=new JButton("添加shell");//创建按钮
        copyShellButton=new JButton("克隆当前shell");//创建按钮
        insertShellButton.addActionListener(new AbstractAction() {//响应单击事件
            @Override
            public void actionPerformed(ActionEvent e) {
                insertShellButtonClick(e);
            }
        });
        copyShellButton.addActionListener(new AbstractAction() {//响应单击事件
            @Override
            public void actionPerformed(ActionEvent e) {
                copyShellButtonClick(e);
            }
        });
        contentPanel.add(currentGodzillaVersionLabel);//添加组件到容器
        contentPanel.add(currentDirLabel);//添加组件到容器
        contentPanel.add(currentUsernameLabel);//添加组件到容器
        contentPanel.add(insertShellButton);//添加组件到容器
        contentPanel.add(copyShellButton);//添加组件到容器
    }

    private void copyShellButtonClick(ActionEvent e){

        Db.addShell(this.shellEntity);//添加当前shell到数据库
        ShellEntity resultShell=Db.getOneShell(Db.getAllShell().lastElement().get(0));//因为我们刚才添加了一条shell 所以最后一条shell就是我们添加的
        MainActivity.getMainActivityFrame().refreshShellView();//刷新shell管理界面的视图
        Log.log( String.format("我添加了一条Id为:%s的shell", resultShell.getId()));//打印日志到控制台
        JOptionPane.showMessageDialog(this.shellEntity.getFrame(), String.format("我添加了一条Id为:%s的shell", resultShell.getId()));//弹框告诉用户添加成功
    }
    private void insertShellButtonClick(ActionEvent e){
        ShellEntity shellEntity=new ShellEntity();//创建一个shell实体
        shellEntity.setUrl("http://127.0.0.1/shell.php");//设置URL
        shellEntity.setPassword("pass");//设置连接密码
        shellEntity.setSecretKey("SuperGodzilla");//设置加密秘钥
        shellEntity.setPayload("JavaDynamicPayload");//设置有效载荷的名字
        shellEntity.setCryption("JAVA_AES_IIOP_T3_HTTP");//设置加密器的名字
        shellEntity.setRemark("这是插件添加的webshell");//设置一个备注
        shellEntity.setProxyHost("127.0.0.1");//设置代理地址
        shellEntity.setProxyPort(8888);//设置代理端口
        shellEntity.setProxyType("NO_PROXY");//设置代理类型 类型种类请看哥斯拉开发Api
        shellEntity.setEncoding("UTF-8");//设置编码类型
        Db.addShell(shellEntity);//添加一条shell到数据库
        ShellEntity resultShell=Db.getOneShell(Db.getAllShell().lastElement().get(0));//因为我们刚才添加了一条shell 所以最后一条shell就是我们添加的
        MainActivity.getMainActivityFrame().refreshShellView();//刷新shell管理界面的视图
        Log.log( String.format("我添加了一条Id为:%s的shell", resultShell.getId()));//打印日志到控制台
        JOptionPane.showMessageDialog(this.shellEntity.getFrame(), String.format("我添加了一条Id为:%s的shell", resultShell.getId()));//弹框告诉用户添加成功
    }

    /***
     * 插件初始化的时候回调用此方法  你可以在这里做一些初始化的操作 比如获取当前路径 但不建议在此处调用payload.include()方法
     * */
    @Override
    public void init(ShellEntity shellContext) {

        this.shellEntity=shellContext;//把shell上下文储存到类变量
        this.payload=this.shellEntity.getPayloadModel();//获取有效载荷并存储到类变量

        this.currentGodzillaVersionLabel.setText(String.format("哥斯拉版本:%s", ApplicationContext.VERSION));
        this.currentDirLabel.setText(String.format("当前web路径:%s", payload.currentDir()));
        this.currentUsernameLabel.setText(String.format("当前用户名:%s", payload.currentUserName()));
    }

    @Override
    public JPanel getView() {
        return contentPanel;//返回内容容器
    }

}
