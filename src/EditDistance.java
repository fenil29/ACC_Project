
public class EditDistance {

	public static int editDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
	 
		int[][] C = new int[len1 + 1][len2 + 1];
	 
		for (int i = 0; i <= len1; i++) 
			C[i][0] = i;
		
		for (int j = 0; j <= len2; j++) 
			C[0][j] = j;
		
		for (int i = 0; i < len1; i++) 
			for (int j = 0; j < len2; j++) 
				if(word1.charAt(i) == word2.charAt(j)) 
					C[i + 1][j + 1] = C[i][j];
				else {
					int replace = C[i][j] + 1;
					int insert = C[i][j + 1] + 1;
					int delete = C[i + 1][j] + 1;
			
					C[i + 1][j + 1] = Math.min(Math.min(replace, insert), delete);
				}	
		return C[len1][len2];
	}
}