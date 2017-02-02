package sample;

/**
 * Created by Роман Лотоцький on 15.01.2017.
 */

import com.itextpdf.text.pdf.CMYKColor;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by roman on 12.01.17.
 */
public class Main extends Application {
    TextField textFieldReadFile, textFieldOutputDirectory, textForFile,
            documentWidth, documentHeight, titleFontSize, paragraphFontSize,
            boardInscriptionFontSize,tFFontFilePath,
            boardCellSize, boardLettersFrameWidth,
            partOfBoardAndButtonsLeftMargin, boardAndButtonsBottomMargin,
            marginAroundMostChessPieces, marginAroundPawnsAndRocksSides,
            textFieldCWhite, textFieldMWhite, textFieldYWhite, textFieldKWhite,
            textFieldCBlack, textFieldMBlack, textFieldYBlack, textFieldKBlack,
            textFieldCStart, textFieldMStart, textFieldYStart, textFieldKStart,
            textFieldCDist, textFieldMDist, textFieldYDist, textFieldKDist;

    CheckBox checkBox;
    PdfConfig config;

    TextField[] arrayTF = new TextField[31];
    List<TextField> listTF = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("PGN to PDF converter");

        config = new PdfConfig();
        ShowProcess showProcess = new ShowProcess();

        textFieldReadFile = new TextField();
        arrayTF[0] = textFieldReadFile;

        textFieldOutputDirectory = new TextField();
        listTF.add(textFieldOutputDirectory);

        setTextToAllFieldsWithDefaultData();

        Button button = new Button("Choose file to read from");
        InputStream inputStream = new FileInputStream("D:/intellij/chess/src/sample/button.jpg");
        Image image22 = new Image(inputStream);
        ImageView imageView2 = new ImageView(image22);
        Tooltip tooltip = new Tooltip();
        tooltip.setGraphic(imageView2);
        bindTooltip(button, tooltip);

        //textFieldReadFile = new TextField();
        arrayTF[0].setOnMouseEntered(e -> arrayTF[0].setPromptText("Full path to input pgn file"));
        arrayTF[0].setOnMouseExited(e-> arrayTF[0].setPromptText(""));
        Tooltip t = new Tooltip("Click button above or insert here full path to the pgn file");
        t.setStyle("-fx-text-fill: black;-fx-background-color:white;-fx-padding:3;");
        bindTooltip(arrayTF[0], t);

        button.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            arrayTF[0].setText(fileChooser.showOpenDialog(primaryStage).toString());
        });

        Button button2 = new Button("Directory to write output");

        listTF.get(0).setOnMouseEntered(e -> textFieldOutputDirectory.setPromptText("Full path to the directory"));
        textFieldOutputDirectory.setOnMouseExited(e-> textFieldOutputDirectory.setPromptText(""));
        Tooltip t2 = new Tooltip("Click button above or insert here full path to the directory to save output file(s)");
        t2.setStyle("-fx-text-fill: black;-fx-background-color:white;-fx-padding:3;");
        bindTooltip(textFieldOutputDirectory, t2);

        button2.setOnAction(e -> {
            DirectoryChooser dChooser = new DirectoryChooser();
            dChooser.setTitle("Choose directory to write");
            textFieldOutputDirectory.setText(dChooser.showDialog(primaryStage).toString());
        });

        Label nameOfFileToWrite = new Label("Name of output file:");
        textForFile = new TextField();

        textForFile.setOnMouseEntered(e -> textForFile.setPromptText("Name pattern of file(s)"));
        textForFile.setOnMouseExited(e-> textForFile.setPromptText(""));
        Tooltip t3 = new Tooltip("Click button above or insert here " +
                "beginning of the resulting pdf file (underscore, sequential " +
                "game number and pdf extension will be added automatically). " +
                "_1.pdf, _2.pdf,... will be generated if the field is empty");
        t3.setWrapText(true);
        t3.setMaxWidth(350);
        t3.setStyle("-fx-text-fill: black;-fx-background-color:white;-fx-padding:3;");
        bindTooltip(textForFile, t3);

        textForFile.setMaxWidth(180);
        Label textFieldPdfExtension = new Label(" .pdf");
//        textFieldPdfExtension.setMaxWidth(50);
        HBox hBoxForTextFile = new HBox();
//        hBoxForTextFile.setAlignment(Pos.CENTER);
        hBoxForTextFile.getChildren().addAll(textForFile, textFieldPdfExtension);
        Button btnGenerate = new Button("Advanced preferences");
        btnGenerate.setOnAction(e -> {
            try {
                generateNewWindow();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

//        Button btnMultipleFile = new Button("Choose multiple files");
//        TextArea textArea = new TextArea();

//        btnMultipleFile.setOnAction(e -> {
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.setTitle("Select PDF files");
//            fileChooser.setInitialDirectory(new File("D:\\робота\\Книги\\"));
//            fileChooser.getExtensionFilters().addAll(
//                    new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
//
//            List<File> selectedFiles = fileChooser.showOpenMultipleDialog(primaryStage);
//            String s = "";
//            for (File f: selectedFiles) {
//                s += f.toString() + "\n";
//            }
//            textArea.setText(s);
//        });

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(button, textFieldReadFile, button2, textFieldOutputDirectory,
                nameOfFileToWrite, hBoxForTextFile);

        Button b = new Button("Generate");
        HBox boxBottom = new HBox();
        boxBottom.setPadding(new Insets(10, 10, 10, 10));
        boxBottom.setAlignment(Pos.BASELINE_RIGHT);
        boxBottom.setSpacing(170);
        boxBottom.getChildren().addAll(btnGenerate, b);

        b.setOnAction(e -> {
            createPdfConfigWithMainWindowParameters();
            validateMainWindowData();
            FxDialogs.showInformation(null, null);
            generateMessage("All files are generated", "");
        });

        //image
        final ImageView imv = new ImageView();
        InputStream is = new FileInputStream("D:/intellij/chess/src/sample/pawn.jpg");
        final Image image2 = new Image(is);
        imv.setImage(image2);

        final VBox pictureRegion = new VBox();
        pictureRegion.setPadding(new Insets(10, 10, 10, 10));
        pictureRegion.getChildren().addAll(imv);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(vBox);
        borderPane.setRight(pictureRegion);
        borderPane.setBottom(boxBottom);

        borderPane.setPadding(new Insets(20, 20, 20, 20));

        Scene scene = new Scene(borderPane, 490, 350);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generateNewWindow() throws FileNotFoundException {

        Label labelGenMultipleFiles = new Label("Generate multiple files  ");
        labelGenMultipleFiles.setFont(Font.font("Default", FontWeight.BOLD, 15));

        checkBox = new CheckBox();
        checkBox.setSelected(true);
        HBox hBoxForGenMultFile = new HBox();
        hBoxForGenMultFile.getChildren().addAll(labelGenMultipleFiles, checkBox);

        Label documentSize = new Label("Document size:");
        documentSize.setFont(Font.font("Default", FontWeight.BOLD, 15));

        Label docWidt = new Label("Width: ");
        documentWidth = new TextField();
        documentWidth.setMaxWidth(50);
        documentWidth.setText(Integer.toString(config.getDocumentWidth()));
        HBox hBoxForWidth = new HBox();
        hBoxForWidth.setSpacing(10);
        hBoxForWidth.setAlignment(Pos.CENTER_LEFT);
        hBoxForWidth.getChildren().addAll(docWidt, documentWidth);

        Label docHeight = new Label("Height:");
        documentHeight = new TextField();
        documentHeight.setMaxWidth(50);
        documentHeight.setText(Integer.toString(config.getDocumentHeight()));
        HBox hBoxForHeight = new HBox();
        hBoxForHeight.setSpacing(10);
        hBoxForHeight.setAlignment(Pos.CENTER_LEFT);
        hBoxForHeight.getChildren().addAll(docHeight, documentHeight);

        VBox vBoxForSize = new VBox();
        vBoxForSize.setSpacing(10);
        vBoxForSize.setPadding(new Insets(10, 10, 10, 10));
        vBoxForSize.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        vBoxForSize.getChildren().addAll(documentSize, hBoxForWidth, hBoxForHeight);

        Label labelFontSize = new Label("Font size");
        labelFontSize.setFont(Font.font("Default", FontWeight.BOLD, 15));

        Label titleFontSizeLabel = new Label("Title font size:                     ");
        titleFontSize = new TextField();
        titleFontSize.setMaxWidth(50);
        titleFontSize.setText(Integer.toString(config.getTitleFontSize()));
        InputStream inputStream = new FileInputStream("D:/intellij/chess/src/sample/button.jpg");
        Image image22 = new Image(inputStream);
        ImageView imageView2 = new ImageView(image22);
        Tooltip tooltip = new Tooltip();
        tooltip.setGraphic(imageView2);
        bindTooltip(titleFontSize, tooltip);
        HBox hBoxForTitleFontSize = new HBox();
        hBoxForTitleFontSize.setSpacing(10);
        hBoxForTitleFontSize.getChildren().addAll(titleFontSizeLabel, titleFontSize);

        Label paragraphFontSizeLabel = new Label("Paragraph font size:           ");
        paragraphFontSize = new TextField();
        paragraphFontSize.setMaxWidth(50);
        paragraphFontSize.setText(Integer.toString(config.getParagraphFontSize()));
        HBox hBoxForParagraphFontSize = new HBox();
        hBoxForParagraphFontSize.setSpacing(10);
        hBoxForParagraphFontSize.getChildren().addAll(paragraphFontSizeLabel, paragraphFontSize);

        Label boardInscrLabel = new Label("Board inscription font size:");
        boardInscriptionFontSize = new TextField();
        boardInscriptionFontSize.setMaxWidth(50);
        boardInscriptionFontSize.setText(Integer.toString(config.getBoardInscriptionFontSize()));
        HBox hBoxForBoardInscr = new HBox();
        hBoxForBoardInscr.setSpacing(10);
        hBoxForBoardInscr.getChildren().addAll(boardInscrLabel, boardInscriptionFontSize);

        VBox vBoxForFontSize = new VBox();
        vBoxForFontSize.setSpacing(10);
        vBoxForFontSize.setPadding(new Insets(10, 10, 10, 10));
        vBoxForFontSize.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        vBoxForFontSize.getChildren().addAll(labelFontSize, hBoxForTitleFontSize, hBoxForParagraphFontSize,
                hBoxForBoardInscr);

        Button fontFilePath = new Button("Font file path");

        tFFontFilePath = new TextField();

        Label labelColorSettings = new Label("Color settings");
        labelColorSettings.setFont(Font.font("Default", FontWeight.BOLD, 15));
        Label colorCells = new Label("Color of white cells:");

        initializeColors();

        HBox hBoxForColorWhite = new HBox();
        hBoxForColorWhite.setAlignment(Pos.CENTER_LEFT);
        hBoxForColorWhite.getChildren().addAll(new Label(" c  "), textFieldCWhite, new Label(" m  "),
                textFieldMWhite, new Label(" y  "), textFieldYWhite, new Label(" k  "), textFieldKWhite);

        HBox hBoxForColorBlack = new HBox();
        hBoxForColorBlack.setAlignment(Pos.CENTER_LEFT);
        hBoxForColorBlack.getChildren().addAll(new Label(" c  "), textFieldCBlack, new Label(" m  "),
                textFieldMBlack, new Label(" y  "), textFieldYBlack,  new Label(" k  "), textFieldKBlack);

        HBox hBoxColorStarting = new HBox();
        hBoxColorStarting.setAlignment(Pos.CENTER_LEFT);
        hBoxColorStarting.getChildren().addAll(new Label(" c  "), textFieldCStart, new Label(" m  "),
                textFieldMStart, new Label(" y  "), textFieldYStart,  new Label(" k  "), textFieldKStart);

        HBox hBoxCellDist = new HBox();
        hBoxCellDist.setAlignment(Pos.CENTER_LEFT);
        hBoxCellDist.getChildren().addAll(new Label(" c  "), textFieldCDist, new Label(" m  "),
                textFieldMDist, new Label(" y  "), textFieldYDist,  new Label(" k  "), textFieldKDist);

        VBox vBoxForColors = new VBox();
        vBoxForColors.setSpacing(10);
        vBoxForColors.setPadding(new Insets(10, 10, 10, 10));
        vBoxForColors.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        vBoxForColors.getChildren().addAll(labelColorSettings, hBoxForColorWhite, hBoxForColorBlack,
                hBoxColorStarting, hBoxCellDist);

        Label boardSize = new Label("Board size");
        boardSize.setFont(Font.font("Default", FontWeight.BOLD, 15));

        Label labelBoardCellSize = new Label("Board cell size:                   ");
        boardCellSize = new TextField();
        boardCellSize.setMaxWidth(50);
        boardCellSize.setText(Integer.toString(config.getBoardCellSize()));
        HBox hBoxCellSize = new HBox();
        hBoxCellSize.setSpacing(10);
        hBoxCellSize.setAlignment(Pos.CENTER_LEFT);
        hBoxCellSize.getChildren().addAll(labelBoardCellSize, boardCellSize);

        Label labelBoardFrame = new Label("Board letters frame width: ");
        boardLettersFrameWidth = new TextField();
        boardLettersFrameWidth.setMaxWidth(50);
        boardLettersFrameWidth.setText(Integer.toString(config.getBoardLettersFrameWidth()));
        HBox hBoxLettersFrame = new HBox();
        hBoxLettersFrame.setSpacing(10);
        hBoxLettersFrame.setAlignment(Pos.CENTER_LEFT);
        hBoxLettersFrame.getChildren().addAll(labelBoardFrame, boardLettersFrameWidth);

        VBox vBoxBoardSize = new VBox();
        vBoxBoardSize.setSpacing(10);
        vBoxBoardSize.setPadding(new Insets(10, 10, 10, 10));
        vBoxBoardSize.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        vBoxBoardSize.getChildren().addAll(boardSize, hBoxCellSize, hBoxLettersFrame);

        Label labelMargins = new Label("Margins to place board"); // to place chess piece...
        labelMargins.setFont(Font.font("Default", FontWeight.BOLD, 15));
        Label labelMarg = new Label("Part of left margin:                ");
        partOfBoardAndButtonsLeftMargin = new TextField();
        partOfBoardAndButtonsLeftMargin.setMaxWidth(50);
        partOfBoardAndButtonsLeftMargin.setText(Integer.
                toString(config.getPartOfBoardAndButtonsLeftMargin()));
        HBox hBoxMarg= new HBox();
        hBoxMarg.setSpacing(10);
        hBoxMarg.setAlignment(Pos.CENTER_LEFT);
        hBoxMarg.getChildren().addAll(labelMarg, partOfBoardAndButtonsLeftMargin);

        Label labelMarg2 = new Label("Bottom margin:                     ");
        boardAndButtonsBottomMargin = new TextField();
        boardAndButtonsBottomMargin.setMaxWidth(50);
        boardAndButtonsBottomMargin.setText(Integer.
                toString(config.getBoardAndButtonsBottomMargin()));
        HBox hBoxMarg2= new HBox();
        hBoxMarg2.setSpacing(10);
        hBoxMarg2.setAlignment(Pos.CENTER_LEFT);
        hBoxMarg2.getChildren().addAll(labelMarg2, boardAndButtonsBottomMargin);

        VBox vBoxMargin = new VBox();
        vBoxMargin.setSpacing(10);
        vBoxMargin.setPadding(new Insets(10, 10, 10, 10));
        vBoxMargin.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        vBoxMargin.getChildren().addAll(labelMargins, hBoxMarg,  hBoxMarg2);

        Label labelMargins2 = new Label("Margins to place chess pieces to cells");
        labelMargins2.setFont(Font.font("Default", FontWeight.BOLD, 15));
        Label labelMarginAround = new Label("Margin around most chess:");
        marginAroundMostChessPieces = new TextField();
        marginAroundMostChessPieces.setMaxWidth(50);
        marginAroundMostChessPieces.setText(Integer.
                toString(config.getMarginAroundMostChessPieces()));
        HBox hBoxMA = new HBox();
        hBoxMA.setSpacing(10);
        hBoxMA.setAlignment(Pos.CENTER_LEFT);
        hBoxMA.getChildren().addAll(labelMarginAround, marginAroundMostChessPieces);

        Label labelAroundPawns = new Label("Margin around pawns:       ");
        marginAroundPawnsAndRocksSides = new TextField();
        marginAroundPawnsAndRocksSides.setMaxWidth(50);
        marginAroundPawnsAndRocksSides.setText(Integer
                .toString(config.getMarginAroundPawnsAndRocksSides()));
        HBox hBoxAP = new HBox();
        hBoxAP.setSpacing(10);
        hBoxAP.setAlignment(Pos.CENTER_LEFT);
        hBoxAP.getChildren().addAll(labelAroundPawns, marginAroundPawnsAndRocksSides);

        VBox vBoxMargin2 = new VBox();
        vBoxMargin2.setSpacing(10);
        vBoxMargin2.setPadding(new Insets(10, 10, 10, 10));
        vBoxMargin2.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        vBoxMargin2.getChildren().addAll(labelMargins2, hBoxMA, hBoxAP);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(5);
        vBox.getChildren().addAll(hBoxForGenMultFile, vBoxForSize, vBoxForFontSize,
                fontFilePath, tFFontFilePath, vBoxForColors);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        Button sumbit = new Button("Submit");

        Button defaultData = new Button("Default data");
        hBox.getChildren().addAll(defaultData, sumbit);

        HBox hBoxSpace = new HBox();
        hBoxSpace.setMinHeight(70);
        VBox vBox2 = new VBox();
        vBox2.setPadding(new Insets(10, 10, 10, 10));
        vBox2.setSpacing(5);
        vBox2.getChildren().addAll(vBoxBoardSize, vBoxMargin, vBoxMargin2, hBoxSpace, hBox);

        BorderPane borderPaneIn = new BorderPane();
        borderPaneIn.setLeft(vBox);
        borderPaneIn.setRight(vBox2);

        Scene scene = new Scene(borderPaneIn, 650, 620);
        Stage stage = new Stage();
        stage.setTitle("Advanced preferences");
        stage.setScene(scene);
        //Fill stage with content
        stage.show();
        defaultData.setOnAction(e -> {
            createPdfConfigWithDefaultData();
//            generateNewWindow();
            stage.hide();
        });
        sumbit.setOnAction(e -> {
            createPdfConfig();
            stage.hide();
        });
        fontFilePath.setOnAction(e ->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            tFFontFilePath.setText(fileChooser.showOpenDialog(stage).toString());
        });
    }

    private void setTextToAllFieldsWithDefaultData(){
        //textFieldReadFile = new TextField();
        textFieldReadFile.setText(config.getInputFilePath());
        //textFieldOutputDirectory = new TextField();
        textFieldOutputDirectory.setText(config.getResultingDirectoryPath());
        textForFile = new TextField();
        textForFile.setText(config.getResultingFileName());
        checkBox = new CheckBox();
        checkBox.setSelected(config.isGenerateMultipleFiles());
        documentWidth = new TextField();
        documentWidth.setText(Integer.toString(config.getDocumentWidth()));
        documentHeight = new TextField();
        documentHeight.setText(Integer.toString(config.getDocumentWidth()));
        titleFontSize = new TextField();
        titleFontSize.setText(Integer.toString(config.getTitleFontSize()));
        boardInscriptionFontSize = new TextField();
        boardInscriptionFontSize.setText(Integer.toString(config.getBoardInscriptionFontSize()));
        tFFontFilePath = new TextField();
        tFFontFilePath.setText(config.getFontFilePath());

        initializeColors();

        boardCellSize = new TextField();
        boardCellSize.setText(Integer.toString(config.getBoardCellSize()));
        boardLettersFrameWidth = new TextField();
        boardLettersFrameWidth.setText(Integer.toString(config.getBoardLettersFrameWidth()));
        partOfBoardAndButtonsLeftMargin = new TextField();
        partOfBoardAndButtonsLeftMargin.setText(Integer.toString(config.getPartOfBoardAndButtonsLeftMargin()));
        boardAndButtonsBottomMargin = new TextField();
        boardAndButtonsBottomMargin.setText(Integer.toString(config.getBoardAndButtonsBottomMargin()));
        marginAroundMostChessPieces = new TextField();
        marginAroundMostChessPieces.setText(Integer.toString(config.getMarginAroundMostChessPieces()));
        marginAroundPawnsAndRocksSides = new TextField();
        marginAroundPawnsAndRocksSides.setText(Integer.toString(config.getMarginAroundPawnsAndRocksSides()));
    }

    private void initializeColors() {
        textFieldCWhite = new TextField(Float.toString(config.getWhiteCellsColor().getCyan()));
        textFieldMWhite = new TextField(Float.toString(config.getWhiteCellsColor().getMagenta()));
        textFieldYWhite = new TextField(Float.toString(config.getWhiteCellsColor().getYellow()));
        textFieldKWhite = new TextField(Float.toString(config.getWhiteCellsColor().getBlack()));

        textFieldCBlack = new TextField(Float.toString(config.getBlackCellsColor().getCyan()));
        textFieldMBlack = new TextField(Float.toString(config.getBlackCellsColor().getMagenta()));
        textFieldYBlack = new TextField(Float.toString(config.getBlackCellsColor().getYellow()));
        textFieldKBlack = new TextField(Float.toString(config.getBlackCellsColor().getBlack()));

        textFieldCStart = new TextField(Float.toString(config.getFromCellColor().getCyan()));
        textFieldMStart = new TextField(Float.toString(config.getFromCellColor().getMagenta()));
        textFieldYStart = new TextField(Float.toString(config.getFromCellColor().getYellow()));
        textFieldKStart = new TextField(Float.toString(config.getFromCellColor().getBlack()));

        textFieldCDist = new TextField(Float.toString(config.getToCellColor().getCyan()));
        textFieldMDist = new TextField(Float.toString(config.getToCellColor().getMagenta()));
        textFieldYDist = new TextField(Float.toString(config.getToCellColor().getYellow()));
        textFieldKDist = new TextField(Float.toString(config.getFromCellColor().getBlack()));

        TextField[] arrayTextField  = {textFieldCWhite, textFieldMWhite, textFieldYWhite, textFieldKWhite,
                textFieldCBlack, textFieldMBlack, textFieldYBlack, textFieldKBlack,
                textFieldCStart, textFieldMStart, textFieldYStart, textFieldKStart,
                textFieldCDist, textFieldMDist, textFieldYDist, textFieldKDist};
        for (TextField e: arrayTextField) {
            e.setMaxWidth(50);
        }
    }

    private void createPdfConfigWithDefaultData() {
        config = new PdfConfig();
    }

    private void createPdfConfigWithMainWindowParameters() {
        createPdfConfigWithDefaultData();
        config.setInputFilePath(textFieldReadFile.getText());
        config.setResultingDirectoryPath(textFieldOutputDirectory.getText());
        config.setResultingFileName(textForFile.getText());
    }

    private void createPdfConfig() {
        validateData();

        config.setInputFilePath(textFieldReadFile.getText());
        config.setResultingDirectoryPath(textFieldOutputDirectory.getText());
        config.setResultingFileName(textForFile.getText());
        config.setDocumentWidth(Integer.parseInt(documentWidth.getText()));
        config.setDocumentHeight(Integer.parseInt(documentHeight.getText()));
        config.setGenerateMultipleFiles(checkBox.isSelected());
        config.setTitleFontSize(Integer.parseInt(titleFontSize.getText()));
        config.setParagraphFontSize(Integer.parseInt(paragraphFontSize.getText()));
        config.setBoardInscriptionFontSize(Integer.parseInt(boardInscriptionFontSize.getText()));
        config.setFontFilePath(tFFontFilePath.getText());

        CMYKColor cmykColor = new CMYKColor(
                Float.parseFloat(textFieldCWhite.getText()),
                Float.parseFloat(textFieldMWhite.getText()),
                Float.parseFloat(textFieldYWhite.getText()),
                Float.parseFloat(textFieldKWhite.getText()));
        config.setWhiteCellsColor(cmykColor);

        config.setBoardCellSize(Integer.parseInt(boardCellSize.getText()));
        config.setBoardLettersFrameWidth(Integer.parseInt(boardLettersFrameWidth.getText()));
        config.setPartOfBoardAndButtonsLeftMargin(Integer.parseInt(partOfBoardAndButtonsLeftMargin.getText()));
        config.setBoardAndButtonsBottomMargin(Integer.parseInt(boardAndButtonsBottomMargin.getText()));
        config.setMarginAroundMostChessPieces(Integer.parseInt(marginAroundMostChessPieces.getText()));
        config.setMarginAroundPawnsAndRocksSides(Integer.parseInt(marginAroundPawnsAndRocksSides.getText()));
        System.out.println(config);
    }

    private void validateMainWindowData() {
        validateAllPaths();
    }

    private void validateData(){
        validateIntergerData(documentWidth, "Width is not number", "Choose number");
        validateIntergerData(documentHeight, "Height is not number", "Choose number for height");
        validateIntergerData(titleFontSize, "Title font size is not number", "Choose number for title font size");
        validateIntergerData(boardInscriptionFontSize, "Board inscription font size is not number", "Choose number for board inscrition");
        validateAllFloatData(textFieldCWhite, textFieldMWhite, textFieldYWhite, textFieldKWhite,
                textFieldCBlack, textFieldMBlack, textFieldYBlack, textFieldKBlack,
                textFieldCStart, textFieldMStart, textFieldYStart, textFieldKStart,
                textFieldCDist,  textFieldMDist,  textFieldYDist,  textFieldKDist);
        validateIntergerData(boardCellSize, "Board cell size is not number", "Choose number for board size.");
        validateIntergerData(boardLettersFrameWidth, "Board letters frame width is not number", "Choose number ");
        validateIntergerData(partOfBoardAndButtonsLeftMargin, "Left margin is not number" , "Choose number");
        validateIntergerData(boardAndButtonsBottomMargin, "Bottom margin is not number", "Choose number");
        validateIntergerData(marginAroundMostChessPieces, "Margin around most chess pieces is not number", "Choose number");
        validateIntergerData(marginAroundPawnsAndRocksSides, "Margin around pawns is not number", "Choose nubmer");
        validateMainWindowData();
    }

    private void validateAllFloatData(TextField... colorSettings) {
        for (int i = 0; i < colorSettings.length; i++) {
            validateSingleFloatData(colorSettings[i].getText(),
                    "Bad number for color", "Choose right number.");
        }
    }

    private int validateIntergerData(TextField documentData, String s1, String s2){
        int number = 0;
        try{
            number = Integer.valueOf(documentData.getText());
        } catch (Exception e){
            generateWarningWindow(s1, s2,  e);
            System.out.println(e);
        }
        return number;
    }

    private float validateSingleFloatData(String documentData, String s1, String s2){
        float number = 0.0f;
        try{
            number = Float.valueOf(documentData);
        } catch (Exception e){
            generateWarningWindow(s1, s2,  e);
            System.out.println(e);
        }
        return number;
    }

    private void validateAllPaths(){
        validateDirectory(textFieldOutputDirectory.getText(), "Wrong directory", "Choose right directory");
        validatePath(textFieldReadFile.getText(), "Wrond file read", "Choose right file");
    }

    private void validateDirectory(String path, String s1, String s2){
        File file = new File(path);
        if (!file.isDirectory())
            generateWarningWindow(s1, s2,  new Exception("Bad directory."));
    }

    private void validatePath(String path, String s1, String s2){
        File file = new File(path);
        if(!file.exists()){
            generateWarningWindow(s1, s2, new InvalidPathException(path, "Unexisted file"));
        }
    }

    private void generateWarningWindow(String s1, String s2, Exception e1){
        Label labelForS1 = new Label(s1);
        Label labelForS2 = new Label(s2);
        Label labelException = new Label(e1.toString());
        labelException.setTextFill(Color.RED);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        Button btnCloseWindow = new Button("Ok");
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.getChildren().addAll(btnCloseWindow);
        BorderPane borderPaneWarn = new BorderPane();
        borderPaneWarn.setCenter(vBox);
        borderPaneWarn.setBottom(hBox);
        vBox.getChildren().addAll(labelForS1, labelForS2, labelException);
        Scene scene = new Scene(borderPaneWarn, 400, 150);
        Stage stage = new Stage();
        stage.setTitle("Generate new files");
        stage.setScene(scene);
        stage.show();
        btnCloseWindow.setOnAction(e -> stage.hide());
    }

    private void generateMessage(String s1, String s2){
        Label labelForS1 = new Label(s1);
        Label labelForS2 = new Label(s2);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        Button btnCloseWindow = new Button("Ok");
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.getChildren().addAll(btnCloseWindow);
        BorderPane borderPaneWarn = new BorderPane();
        borderPaneWarn.setCenter(vBox);
        borderPaneWarn.setBottom(hBox);
        vBox.getChildren().addAll(labelForS1, labelForS2);
        Scene scene = new Scene(borderPaneWarn, 400, 150);
        Stage stage = new Stage();
        stage.setTitle("Generate new files");
        stage.setScene(scene);
        stage.show();
        btnCloseWindow.setOnAction(e -> stage.hide());
    }

    public static void bindTooltip(final TextField node, final Tooltip tooltip) {
        node.setOnMouseMoved(e -> tooltip.show(node, e.getScreenX(), e.getScreenY() + 15));
        node.setOnMouseExited(e -> tooltip.hide());
    }

    public static void bindTooltip(final Button node, final Tooltip tooltip) {
        node.setOnMouseMoved(e -> tooltip.show(node, e.getScreenX(), e.getScreenY() + 15));
        node.setOnMouseExited(e -> tooltip.hide());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
