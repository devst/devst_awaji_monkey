package answers;

/**
 * 50音変換
 * 
 * アルファベットの文字列を渡して、ローマ字読みでそれに当てはまる50音のひらがな文字を文字列として返す。
 * ただし、渡す文字列は半角の2文字までで、例えば「ち」は "chi" ではなく "ti" で表現する。
 */

public interface Answer2 {

	String execute(char... str);
}
