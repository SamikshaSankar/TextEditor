import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring properties of text Editor
    JFrame frame;//package
    JMenuBar menuBar;

    JMenu file,edit;


    //Under file new items
    JMenuItem newFile, openFile, saveFile;

    //Under  edit items
    JMenuItem cut, copy, paste, selectAll,fontSize,fontColor,close;

    //textarea
    JTextArea textArea;


    //constructor
    TextEditor(){//whenever this text editor constructor called text frame will be created
        //Initialize a frame
        frame = new JFrame();

        //Initialized menuBar
        menuBar = new JMenuBar();

        //Initialized text area
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN,18));//default font size


        //Initialized menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initialized file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //Add action Listeners to the file menu items
        newFile.addActionListener(this);//this keyword listen to the particular file item
        openFile.addActionListener(this);
        saveFile.addActionListener(this);


        //add file menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialized edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        fontSize = new JMenuItem("Font Size");
        fontColor = new JMenuItem("Font Color");
        close = new JMenuItem("Close");

        //Add action Listeners to the edit menu items
        cut.addActionListener(this);//this keyword listen to the particular edit item
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        fontSize.addActionListener(this);
        fontColor.addActionListener(this);
        close.addActionListener(this);

        //add edit menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(fontSize);
        edit.add(fontColor);
        edit.add(close);

        //added menus into menubar
        menuBar.add(file);
        menuBar.add(edit);

        //set menuBar to frame
        frame.setJMenuBar(menuBar);//setJMenuBar-dedicated function for menu bar

        //create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add text area to panel
        panel.add(textArea, BorderLayout.CENTER);

        //Create Scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Add scroll pane to panel
        panel.add(scrollPane);

        //Add panel to frame
        frame.add(panel);

        //set dimensions
        frame.setBounds(550,250,400,400);//(0,0 means top corner)
        frame.setTitle("Text Editor");
        frame.setVisible(true);//frame not be hidden
        frame.setLayout(null);


    }
            
    @Override//whatever action happens inside the texteditor class we need to define function for that action inside this method
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){//59
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            //perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            //perform selectAll operation
            textArea.selectAll();
        }

        //change the fontsize by value
        if (actionEvent.getSource()==fontSize){

            String sizeOfFont = JOptionPane.showInputDialog(frame,"Enter Font Size",JOptionPane.OK_CANCEL_OPTION);
            if (sizeOfFont != null){
                int convertSizeOfFont = Integer.parseInt(sizeOfFont);
                Font font = new Font(Font.SANS_SERIF,Font.PLAIN,convertSizeOfFont);
                textArea.setFont(font);
            }
        }

        //fontcolour
        if(actionEvent.getSource()==fontColor) {
            JColorChooser colorChooser = new JColorChooser();

            Color color = colorChooser.showDialog(null, "Choose a color", Color.black);

            textArea.setForeground(color);
        }

        if(actionEvent.getSource()==close){
            //perform close editor operation
            System.exit(0);//0 means we closed the application
        }
        if(actionEvent.getSource()==openFile){
            //open file chooser
            JFileChooser fileChooser = new JFileChooser("C:");//path of c drive only
            int chooseOption = fileChooser.showOpenDialog(null);//open button
            //click on open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //getting selected file
                File file = fileChooser.getSelectedFile();
                //get the path of the selected file
                String filePath = file.getPath();
                try{
                    //initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);//read the content of file
                    String intermediate = "", output="";
                    //read contents of file line by line
                    while((intermediate = bufferedReader.readLine())!=null){
                        output += intermediate+"\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                }
                catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }

            }
        }
        if(actionEvent.getSource()==saveFile){
            //Initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            //set change option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);//save button
            //check if we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try{
                    //initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String intermediate = "", output="";
                    //write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        //function for clicking on new window
        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
       //instance of text editor
        TextEditor textEditor = new TextEditor();//whenever this triggered text application will be created

    }
}