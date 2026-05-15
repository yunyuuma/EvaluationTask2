package DummyFile;

class InputCheck {

    private String str;

    public InputCheck(String str) {
        // 不具合：nullが渡された場合、後続のlength()やparseInt()で例外になる可能性がある。
        // 修正内容：nullの場合は空文字に変換する。
        if (str == null) {
            str = "";
        }
        this.str = str;
    }

    protected boolean isNumeric() {

        // 不具合：String比較に「==」を使用しており、空文字判定が正しく動かない可能性がある。
        // 修正内容：isEmpty()を使用して文字列の中身で判定する。
        if (this.str.isEmpty()) {
            return false;
        }

        for (int i = 0; i < this.str.length(); i++) {
            char c = this.str.charAt(i);

            // 不具合：数字以外が含まれていてもparseInt前に正しく除外する必要がある。
            // 修正内容：'0'～'9'以外はfalseを返す。
            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }

    protected boolean checkSize() {

        // 不具合：桁数だけで判定すると、parseInt時に想定外の例外が発生する可能性がある。
        // 修正内容：try-catchで数値変換エラーを防止する。
        try {
            int size = Integer.parseInt(this.str);

            // 不具合：Math.abs()を使うと負数を正数に変換してしまい、不正な入力を許可する可能性がある。
            // 修正内容：isNumeric()で数字のみ許可しているためMath.abs()を削除し、0以上100MB以下で判定する。
            return size >= 0 && size <= 1024 * 1024 * 100;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}