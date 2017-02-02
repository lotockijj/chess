package sample;

import com.itextpdf.text.pdf.CMYKColor;

/**
 * Created by Роман Лотоцький on 16.01.2017.
 */
public class PdfConfig {
    //    public String inputFilePath = "/mnt/mystorage/notes/focus/programming/YaroslavTraining/PgnToPdf/etc/pgn_files/Akobian.pgn";
//    public String inputFilePath = "/mnt/mystorage/notes/focus/programming/YaroslavTraining/PgnToPdf/etc/pgn_files/Ivanchuk.pgn";
    private String inputFilePath = "/mnt/mystorage/notes/focus/programming/YaroslavTraining/PgnToPdf/etc/pgn_files/1game.pgn";
    private String resultingDirectoryPath = "/mnt/mystorage/notes/focus/programming/YaroslavTraining/PgnToPdf/etc/";
    private String resultingFileName = "chess_game";
    private String fontFilePath = "/mnt/mystorage/notes/focus/programming/YaroslavTraining/PgnToPdf/src/main/resources/ufonts.com_arial-unicode-ms.ttf";
    // fixme: is used during creation - should be deleted
    private String resultingFilePath = resultingDirectoryPath + resultingFileName + ".pdf";
    // If png file has many games inside - one or many files can be generated
    // Pattern for multiple files is resultingFileName_000.pdf, where 000 is a game number
    private boolean generateMultipleFiles = false;
    // All dimensions indicated in user units: 1 in. = 25.4 mm = 72 user units
    private int documentWidth = 780;
    private int documentHeight = 600;
    // Info about fonts: fixme
    private int titleFontSize = 16;
    private int paragraphFontSize = 16;
    private int boardInscriptionFontSize = 12;
    // Colors of chess board cells: fixme
    private CMYKColor whiteCellsColor = new CMYKColor(0f, 0.10f, 0.26f, 0.07f);
    private CMYKColor blackCellsColor = new CMYKColor(0f, 0.29f, 0.5f, 0.48f);
    private CMYKColor fromCellColor = new CMYKColor(0.33f, 0.33f, 0f, 0f);
    private CMYKColor toCellColor = new CMYKColor(0.33f, 0.49f, 0f, 0f);
    //fixme
    private int buttonsColor = 26;

    // Board size
    private int boardCellSize = 36;
    private int boardLettersFrameWidth = 13;
    private int stashElementsQuantity = 49;
    // margins to place board inside the pdf file:
    private int partOfBoardAndButtonsLeftMargin = 18;
    private int boardAndButtonsLeftMargin
            = partOfBoardAndButtonsLeftMargin + boardCellSize
            - boardLettersFrameWidth;
    private int boardAndButtonsBottomMargin = 54;
    // to draw chess pieces properly:
    private int marginAroundMostChessPieces = 4;
    private int marginAroundPawnsAndRocksSides = 8;
    // to calculate movements inside JS code:
    private int boardX0 = boardCellSize + partOfBoardAndButtonsLeftMargin;
    private int boardY0 = boardCellSize * 2 + boardAndButtonsBottomMargin;
    private int longestMoveDuration = 100; // duration of move from a1 to h8

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getResultingDirectoryPath() {
        return resultingDirectoryPath;
    }

    public void setResultingDirectoryPath(String resultingDirectoryPath) {
        this.resultingDirectoryPath = resultingDirectoryPath;
    }

    public String getResultingFileName() {
        return resultingFileName;
    }

    public void setResultingFileName(String resultingFileName) {
        this.resultingFileName = resultingFileName;
    }

    public String getFontFilePath() {
        return fontFilePath;
    }

    public void setFontFilePath(String fontFilePath) {
        this.fontFilePath = fontFilePath;
    }

    public String getResultingFilePath() {
        return resultingFilePath;
    }

    public void setResultingFilePath(String resultingFilePath) {
        this.resultingFilePath = resultingFilePath;
    }

    public boolean isGenerateMultipleFiles() {
        return generateMultipleFiles;
    }

    public void setGenerateMultipleFiles(boolean generateMultipleFiles) {
        this.generateMultipleFiles = generateMultipleFiles;
    }

    public int getDocumentWidth() {
        return documentWidth;
    }

    public void setDocumentWidth(int documentWidth) {
        this.documentWidth = documentWidth;
    }

    public int getDocumentHeight() {
        return documentHeight;
    }

    public void setDocumentHeight(int documentHeight) {
        this.documentHeight = documentHeight;
    }

    public int getTitleFontSize() {
        return titleFontSize;
    }

    public void setTitleFontSize(int titleFontSize) {
        this.titleFontSize = titleFontSize;
    }

    public int getParagraphFontSize() {
        return paragraphFontSize;
    }

    public void setParagraphFontSize(int paragraphFontSize) {
        this.paragraphFontSize = paragraphFontSize;
    }

    public int getBoardInscriptionFontSize() {
        return boardInscriptionFontSize;
    }

    public void setBoardInscriptionFontSize(int boardInscriptionFontSize) {
        this.boardInscriptionFontSize = boardInscriptionFontSize;
    }

    public CMYKColor getWhiteCellsColor() {
        return whiteCellsColor;
    }

    public void setWhiteCellsColor(CMYKColor whiteCellsColor) {
        this.whiteCellsColor = whiteCellsColor;
    }

    public CMYKColor getBlackCellsColor() {
        return blackCellsColor;
    }

    public void setBlackCellsColor(CMYKColor blackCellsColor) {
        this.blackCellsColor = blackCellsColor;
    }

    public CMYKColor getFromCellColor() {
        return fromCellColor;
    }

    public void setFromCellColor(CMYKColor fromCellColor) {
        this.fromCellColor = fromCellColor;
    }

    public CMYKColor getToCellColor() {
        return toCellColor;
    }

    public void setToCellColor(CMYKColor toCellColor) {
        this.toCellColor = toCellColor;
    }

    public int getButtonsColor() {
        return buttonsColor;
    }

    public void setButtonsColor(int buttonsColor) {
        this.buttonsColor = buttonsColor;
    }

    public int getBoardCellSize() {
        return boardCellSize;
    }

    public void setBoardCellSize(int boardCellSize) {
        this.boardCellSize = boardCellSize;
    }

    public int getBoardLettersFrameWidth() {
        return boardLettersFrameWidth;
    }

    public void setBoardLettersFrameWidth(int boardLettersFrameWidth) {
        this.boardLettersFrameWidth = boardLettersFrameWidth;
    }

    public int getStashElementsQuantity() {
        return stashElementsQuantity;
    }

    public void setStashElementsQuantity(int stashElementsQuantity) {
        this.stashElementsQuantity = stashElementsQuantity;
    }

    public int getPartOfBoardAndButtonsLeftMargin() {
        return partOfBoardAndButtonsLeftMargin;
    }

    public void setPartOfBoardAndButtonsLeftMargin(int partOfBoardAndButtonsLeftMargin) {
        this.partOfBoardAndButtonsLeftMargin = partOfBoardAndButtonsLeftMargin;
    }

    public int getBoardAndButtonsLeftMargin() {
        return boardAndButtonsLeftMargin;
    }

    public void setBoardAndButtonsLeftMargin(int boardAndButtonsLeftMargin) {
        this.boardAndButtonsLeftMargin = boardAndButtonsLeftMargin;
    }

    public int getBoardAndButtonsBottomMargin() {
        return boardAndButtonsBottomMargin;
    }

    public void setBoardAndButtonsBottomMargin(int boardAndButtonsBottomMargin) {
        this.boardAndButtonsBottomMargin = boardAndButtonsBottomMargin;
    }

    public int getMarginAroundMostChessPieces() {
        return marginAroundMostChessPieces;
    }

    public void setMarginAroundMostChessPieces(int marginAroundMostChessPieces) {
        this.marginAroundMostChessPieces = marginAroundMostChessPieces;
    }

    public int getMarginAroundPawnsAndRocksSides() {
        return marginAroundPawnsAndRocksSides;
    }

    public void setMarginAroundPawnsAndRocksSides(int marginAroundPawnsAndRocksSides) {
        this.marginAroundPawnsAndRocksSides = marginAroundPawnsAndRocksSides;
    }

    public int getBoardX0() {
        return boardX0;
    }

    public void setBoardX0(int boardX0) {
        this.boardX0 = boardX0;
    }

    public int getBoardY0() {
        return boardY0;
    }

    public void setBoardY0(int boardY0) {
        this.boardY0 = boardY0;
    }

    public int getLongestMoveDuration() {
        return longestMoveDuration;
        //whiteCellsColor.getCyan();

    }

    public void setLongestMoveDuration(int longestMoveDuration) {
        this.longestMoveDuration = longestMoveDuration;
    }

    @Override
    public String toString() {
        return "PdfConfig{" +
                "inputFilePath='" + inputFilePath + '\'' +
                ", resultingDirectoryPath='" + resultingDirectoryPath + '\'' +
                ", resultingFileName='" + resultingFileName + '\'' +
                ", fontFilePath='" + fontFilePath + '\'' +
                ", resultingFilePath='" + resultingFilePath + '\'' +
                ", generateMultipleFiles=" + generateMultipleFiles +
                ", documentWidth=" + documentWidth +
                ", documentHeight=" + documentHeight +
                ", titleFontSize=" + titleFontSize +
                ", paragraphFontSize=" + paragraphFontSize +
                ", boardInscriptionFontSize=" + boardInscriptionFontSize +
                ", whiteCellsColor=" + whiteCellsColor +
                ", blackCellsColor=" + blackCellsColor +
                ", fromCellColor=" + fromCellColor +
                ", toCellColor=" + toCellColor +
                ", buttonsColor=" + buttonsColor +
                ", boardCellSize=" + boardCellSize +
                ", boardLettersFrameWidth=" + boardLettersFrameWidth +
                ", stashElementsQuantity=" + stashElementsQuantity +
                ", partOfBoardAndButtonsLeftMargin=" + partOfBoardAndButtonsLeftMargin +
                ", boardAndButtonsLeftMargin=" + boardAndButtonsLeftMargin +
                ", boardAndButtonsBottomMargin=" + boardAndButtonsBottomMargin +
                ", marginAroundMostChessPieces=" + marginAroundMostChessPieces +
                ", marginAroundPawnsAndRocksSides=" + marginAroundPawnsAndRocksSides +
                ", boardX0=" + boardX0 +
                ", boardY0=" + boardY0 +
                ", longestMoveDuration=" + longestMoveDuration +
                '}';
    }
}