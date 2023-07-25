import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //declare properties of TextEditor
    JFrame frame;

    JMenuBar menuBar;

    JMenu file,edit;

    JMenuItem newFile,openFile ,saveFile;
    JMenuItem cut,copy,paste,selectAll,close;

    JTextArea textArea;

    TextEditor(){

        //initialize a frame
        frame=new JFrame();
        //initialize menu
        menuBar=new JMenuBar();

        //initilazie text area
        textArea =new JTextArea();


        // initialize menu
        file = new JMenu( "File");
        edit = new JMenu("Edit");

        //initialize file menu items
        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");

        //add actionlistener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initilaize edit menu items
        cut =new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        //adding actionlistener to edit menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        //add to menu bar
        menuBar.add(file);
        menuBar.add(edit);




        //set menu to frame
        frame.setJMenuBar(menuBar);
        //add text area to frame
       // frame.add(textArea);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area to frame
        panel.add(textArea,BorderLayout.CENTER);
        //create Scroll pane

        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);

        // set dimensions
        frame.setBounds(100,100,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
     public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            textArea.paste();

        }
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showOpenDialog(null);
            //if we have to clicked in open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file =fileChooser.getSelectedFile();
                //get path of selected file
                String filePath = file.getPath();
                try{
                    //initialize the file reader
                    FileReader fileReader=new FileReader(filePath);
                    //initialize buffer reader
                    BufferedReader bufferedReader =new BufferedReader(fileReader);
                    String intermediate ="",output="";
                    //read content of file line by line
                    while((intermediate= bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";

                    }
                    //set the output string to text area
                    textArea.setText(output);
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }

            }
        }

        if(actionEvent.getSource()==saveFile) {
            //initialize file
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if  we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION) {
                //create a new file
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + "txt");
                try {
                    FileWriter fileWriter=new FileWriter(file);
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);


                }
                catch (IOException ioException) {
                    ioException.printStackTrace();

                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor textEditor= new TextEditor();
        }

    }
    public static void main(String[] args)
    {
        TextEditor textEditor = new TextEditor();
    }
}