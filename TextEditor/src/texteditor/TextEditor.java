/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;
import java.awt.Font;
import static java.awt.Font.PLAIN;
import static java.awt.Font.SERIF;
import javax.swing.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author admin
 */
public class TextEditor extends JFrame implements ActionListener{
    JMenuBar menubar = new JMenuBar();
    JMenu file=new JMenu("File");
    JMenu edit=new JMenu("Edit");
    JMenu Font=new JMenu("Font");
    JMenu help=new JMenu("Help");
    JMenuItem neww =new JMenuItem("New");
    JMenuItem open=new JMenuItem("Open");
    JMenuItem save=new JMenuItem("Save");
    JMenuItem print=new JMenuItem("Print");
    JMenuItem exit=new JMenuItem("Exit");
    JMenuItem cut=new JMenuItem("Cut");
    JMenuItem copy=new JMenuItem("Copy");
    JMenuItem paste=new JMenuItem("Paste");
    JMenuItem selectAll=new JMenuItem("SelectAll");
    JTextArea textarea=new JTextArea();
    
TextEditor(){
 setTitle("TextEditor");
 setBounds(100,100,800,600);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setJMenuBar(menubar);
menubar.add(file);
menubar.add(edit);
menubar.add(help);
file.add(neww);
file.add(open);
file.add(save);
file.add(print);
file.add(exit);
edit.add(cut);
edit.add(copy);
edit.add(paste);
edit.add(selectAll);
ImageIcon img= new ImageIcon(getClass().getResource("blocnotes.png"));
    setIconImage(img.getImage());
    JScrollPane scrollpane =new JScrollPane(textarea);
     add(scrollpane);
    textarea.setFont(new Font(SERIF,PLAIN, 17));
    textarea.setLineWrap(true);
    textarea.setWrapStyleWord(true);
neww.addActionListener(this);
open.addActionListener(this);
save.addActionListener(this);
print.addActionListener(this);
exit.addActionListener(this);
cut.addActionListener(this);
copy.addActionListener(this);
paste.addActionListener(this);
selectAll.addActionListener(this);
}
/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new TextEditor().setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     
    if(e.getActionCommand().equalsIgnoreCase("New")){
        textarea.setText(null);
    }else if(e.getActionCommand().equalsIgnoreCase("Open")){
         JFileChooser fileChooser=new JFileChooser();
        FileNameExtensionFilter textFilter=new FileNameExtensionFilter("Only Text File(.txt)","txt");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(textFilter);
         int action = fileChooser.showOpenDialog(null);
        if(action!=JFileChooser.APPROVE_OPTION){
            return;
        
        }else{
           
            try{
                BufferedReader reader=new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                textarea.read(reader,null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }else if(e.getActionCommand().equalsIgnoreCase("Save")){
        JFileChooser fileChooser=new JFileChooser();
        FileNameExtensionFilter textFilter=new FileNameExtensionFilter("Only Text File(.txt)","txt");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(textFilter);
        int action = fileChooser.showSaveDialog(null);
        if(action!=JFileChooser.APPROVE_OPTION){
            return;
        
        }else{
            String FileName=fileChooser.getSelectedFile().getAbsolutePath().toString();
            if(!FileName.contains(".txt"))
                FileName=FileName+".txt";
            try{
                BufferedWriter writer=new BufferedWriter(new FileWriter(FileName));
                textarea.write(writer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }else if(e.getActionCommand().equalsIgnoreCase("Print")){
       
        try {
            textarea.print();
        } catch (PrinterException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }else if(e.getActionCommand().equalsIgnoreCase("Exit")){
        System.exit(0);
    }else if(e.getActionCommand().equalsIgnoreCase("Cut")){
        textarea.cut();
    }else if(e.getActionCommand().equalsIgnoreCase("Copy")){
        textarea.copy();
          }else if(e.getActionCommand().equalsIgnoreCase("Paste")){
        textarea.paste();
    
    }else if(e.getActionCommand().equalsIgnoreCase("SelectAll")){
        textarea.selectAll();
    }

    
}
}
