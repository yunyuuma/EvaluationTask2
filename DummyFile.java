package DummyFile;

import java.util.Scanner;

public class DummyFile {

    public static void main(String[] args) {

        try (Scanner scan = new Scanner(System.in)) {

            System.out.println("作成するファイルの100MB以下のバイト数(104857600)を入力してください。");
            String str = scan.nextLine();

            InputCheck ic = new InputCheck(str);

            if (ic.isNumeric()) {
                if (ic.checkSize()) {

                    // 不具合：Math.abs()を使うと負数入力が正数として扱われる可能性がある。
                    // 修正内容：入力チェック済みの値をそのまま数値変換する。
                    int fileSize = Integer.parseInt(str);

                    if (CreateFile.createDummyFile(fileSize)) {

                        String outPutFileSize = "";

                        if (fileSize >= (1024 * 1024)) {
                            outPutFileSize = fileSize / (1024 * 1024) + "MB";
                        } else if (fileSize >= 1024) {
                            outPutFileSize = fileSize / 1024 + "KB";
                        } else {
                            outPutFileSize = fileSize + "B";
                        }

                        System.out.println(outPutFileSize + "のファイルを出力しました。");

                    } else {
                        System.out.println("エラー：ファイル作成に失敗しました。");
                    }

                } else {
                    System.out.println("エラー：100MB以下のバイト数(104857600)を指定してください。");
                }

            } else {
                System.out.println("エラー：数値のみ入力してください。");
            }
        }
    }
}