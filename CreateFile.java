package DummyFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

class CreateFile {

    protected static boolean createDummyFile(int byteSize) {

        File file = new File("./DummyFile");

        // 不具合：delete()の戻り値を確認していないため、削除に失敗しても処理が続いてしまう。
        // 修正内容：既存ファイルがあり、削除に失敗した場合はfalseを返す。
        if (file.exists() && !file.delete()) {
            return false;
        }

        try (RandomAccessFile rFile = new RandomAccessFile(file.getPath(), "rw")) {

            rFile.setLength(byteSize);

            // 不具合：try-with-resourcesを使用しているのにclose()を手動で呼んでいる。
            // 修正内容：try-with-resourcesにより自動でcloseされるため、手動closeを削除する。

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}