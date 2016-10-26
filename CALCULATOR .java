package Expression;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/*

Ŀ�ģ�����ʽ�� ����   ��ֵ     ��

���� :  ����ʽ����
        1.������Ļ����
        2.�ж� �Ϸ����
        3.������չ �Ϸ��ж�
                      ��������
        1.��
        2.��ֵ
��ֵ :  1.


 */

class Term {
	public int coefficient;/* fei static */
	public int fuhao;
	public String[] quantity = null;
	public int[] index;

	Term() {
		coefficient = 1;
		quantity = new String[] { "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" };
		index = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		fuhao = 1;
	}
}

public class CALCULATOR {

	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		Scanner scnn = new Scanner(System.in);
		String input = scnn.nextLine();
		// ch.close();
		String str = "";
		Term[] n = new Term[10];
		for (int i = 0; i < 10; i++) { // ����
			n[i] = new Term();
		}

		while (!Judge(input).equals("Exit")) {
			// System.out.println("k1");
			if (Judge(input).equals("Expression")) {
				System.out.println("Correct Expression!");
				long a = System.currentTimeMillis();
				str = Arrangement_n(input, n);
				str = Arrangement_exp(str, n);
				str = Arrangement_n(str, n);
				if (str.charAt(0) != '-') {
					str = str.substring(1);
				}
				System.out.println(str);
				System.out.println("\r<br>ִ�к�ʱ : " + (System.currentTimeMillis() - a) / 1000f + " �� ");
			} else if (Judge(input).equals("Derivative")) {
				// �� ����
				// System.out.println("1111111");
				long a = System.currentTimeMillis();
				str = derivative(input, str, n);
				for (int i = 0; i < 10; i++) { // ����
					n[i] = new Term();
				}
				str = Arrangement_n(str, n); // �õ��ַ���
				str = Arrangement_exp(str, n);
				str = Arrangement_n(str, n);
				if (str.charAt(0) != '-') {
					str = str.substring(1);
				}
				System.out.println(str);
				System.out.println("\r<br>ִ�к�ʱ : " + (System.currentTimeMillis() - a) / 1000f + " �� ");
			} else if (Judge(input).equals("simplify")) {
				// ����
				long crntm = System.currentTimeMillis();
				str = Simplify(input, str, n);
				System.out.println(str);
				for (int i = 0; i < 10; i++) { // ����
					n[i] = new Term();
				}
				str = Arrangement_n(str, n); // �õ��ַ���
				str = Arrangement_exp(str, n);
				str = Arrangement_n(str, n);
				if (str.charAt(0) != '-'){
					str = str.substring(1);
				}	
				System.out.println(str);
				System.out.println("\r<br>ִ�к�ʱ : " + (System.currentTimeMillis() - crntm) / 1000f + " �� ");
			}

			else if (Judge(input).equals("NULL")) {
				long a = System.currentTimeMillis();
				// str =Arrangement(str, n);
				System.out.println(str);
				System.out.println("\r<br>ִ�к�ʱ : " + (System.currentTimeMillis() - a) / 1000f + " �� ");
			} else {
				System.out.println("Wrong Expression!please continue!");
			}
			scnn = new Scanner(System.in);
			input = scnn.nextLine();
			// System.out.println("***************"+input);
		}
		scnn.close();
	}

	// ��
	public static String derivative(final String input, final String str, final Term[] n) {
		// System.out.println(input);
		// System.out.println(str);
		String strrrr = "";
		String strTemp = input.substring(4).trim();
		boolean flag = false;
		String regex = "[\\+-]";
		String[] arr1 = str.split(regex);
		for (int k1 = 0; k1 < 10; k1++) {
			for (int k2 = 0; k2 < 10; k2++) {
				if (strTemp.equals(n[k1].quantity[k2])) {
					flag = true;
				}
			}
		}

		// System.out.println(str.charAt(str.indexOf(arr1[i1+1])-1));
		if (flag) { // ���� Ŀ�����
			int i1 = 0; // ��ļ���
			for (String s3 : arr1) {
				flag = false;
				for (int i2 = 0; i2 < 10; i2++) {
					if (strTemp.equals(n[i1].quantity[i2])) {
						n[i1].coefficient = n[i1].coefficient * n[i1].index[i2];
						n[i1].index[i2]--;
						flag = true;
						break;
					}
				}
				if (!flag) { // ��� ������� ��Ŀ��
					strrrr = strrrr + "";
				} else { // ���������� ��Ŀ��
					if (n[i1].fuhao == 1) {
						strrrr = strrrr + '+';
					} else{
						strrrr = strrrr + '-';
					}
						
					strrrr = strrrr + n[i1].coefficient;
					for (int i3 = 0; !n[i1].quantity[i3].equals("1"); i3++) {
						for (int i4 = 0; i4 < n[i1].index[i3]; i4++) {
							strrrr = strrrr + "*" + n[i1].quantity[i3];
						}
					}
				}
				i1++;
			}
		} else {
			System.out.println("no variable!");
			return str;
		}
		if (strrrr.equals("")) { // Ϊ 0
			strrrr = "0";
		}
		if (strrrr.charAt(0) != '-')
			strrrr = strrrr.substring(1, strrrr.length());
		return strrrr;
	}
	// 3*4*5*6*m*m*m*v*v*9+6*c+h*9
	// 2*3*4 * 6 * m+ k*p* 1 + lll* 5
	// 5*5+6*6*a+9*d*gg+9
	// !d/d m

	// �ж�����
	public static String Judge(String input) { // �ж�

		String regex2;
		if (input.charAt(0) == '!') { // command
			if (input.equals("!Exit")) {
				return "Exit";
			}
			regex2 = "!simplify";
			if (input.matches(regex2)) {
				// System.out.println(input);
				return "NULL";
			}
			regex2 = "(!simplify([ ]*[a-zA-Z]*[ ]*=[ ]*[1-9]\\d*[ ]*)*)";
			if (input.matches(regex2)) {
				// System.out.println("Simplify");
				return "simplify";
			}
			regex2 = "!d/d[ ]*[a-zA-Z]*";
			if (input.matches(regex2)) {
				// System.out.println("Derivative");
				return "Derivative";
			}
			// System.out.println("illegal input!");
			return "Error!";
		}

		else { // expression
			regex2 = "([ ]*-?[ ]*([1-9]\\d*\\.?\\d*|[a-zA-Z]*)[ ]*([\\+-]|[\\*]))*[ ]*(([1-9]\\d*\\.?\\d*)|[a-zA-Z]*[ ]*)";
			if (input.matches(regex2)) {
				return "Expression";
			} else {
				// System.out.println("Wrong Expression");
				return "Wrong Expression";
			}
		}
		// return "Error!";
	}

	// ��ֵ���뻯��
	public static String Simplify(String input, String str, Term[] n) {
		String regex = "[a-zA-Z]*[ ]*=[ ]*[1-9]\\d*";
		Pattern p = Pattern.compile(regex);
		Matcher mtch = p.matcher(input);
		while (mtch.find()) {

			String strTemp = mtch.group();
			strTemp = strTemp.replaceAll("[ ]*", "");
			String quantity = strTemp.substring(0, strTemp.indexOf("="));
			String value = strTemp.substring(strTemp.indexOf("=") + 1, strTemp.length());
			if (str.indexOf(quantity) >= 0) {
				str = str.replaceAll(quantity, value);
			} else {
				System.out.println("Sorry no variable!" + ":" + quantity);
			}
		}
		for (int i = 0; i < 10; i++) { // ����
			n[i] = new Term();
		}
		return str;
	}

	// ������ʽ
	public static String Arrangement_exp(String string, Term[] n) {
		// �ϲ����г�����

		int tempChangshu = 0;
		string = string.replaceAll("([\\+-])", " $1"); // ����ÿһ��ķ���
		String regex2 = " ";
		String[] arr2 = string.split(regex2); // ÿһ�������
		String strrrr = "";
		for (int f = 1; f < arr2.length; f++) {
			if ((!arr2[f].matches(".*[A-Za-z]+.*"))) { // ���ּ�������
				String kString = arr2[f].substring(1);
				if (arr2[f].charAt(0) == '+') {
					tempChangshu = tempChangshu + Integer.parseInt(kString);
				} else {
					tempChangshu = tempChangshu - Integer.parseInt(kString);
				}
					
				arr2[f] = "";
			}
			strrrr = strrrr + arr2[f];
		}
		if (tempChangshu != 0) {
			strrrr = tempChangshu + strrrr;
		}
			
		return strrrr;
	}

	// ���� Term n
	public static String Arrangement_n(String input, Term[] n) { // �洢�ṹ�� ������ʽ
		// ����zheng li
		// System.out.println(input);
		if (input.charAt(0) != '-' && input.charAt(0) != '+'){
			input = "+" + input;
		}
		input = input.replaceAll("[ ]*", ""); // ��
		input = input.replaceAll("([\\+-])", " $1"); // ����ÿһ��ķ���

		String regex = " ";
		String[] arr1 = input.split(regex); // ÿһ�������

		for (int i = 0; i < 10; i++) { // ����
			n[i] = new Term();
		}

		int tempCoefficient = 1;
		int tempN1 = 0;
		int tempN2 = 0;
		int pos = 0;
		int m = 0;
		String string = "";
		boolean flag = false;

		for (m = 1; m < arr1.length; m++) { // ����ṹ��
			// ����ÿһ��
			// ******����
			String s1 = arr1[m];
			if (s1.charAt(0) == '+') {
				n[m - 1].fuhao = 1;
				string = string + "+";
			} else {
				n[m - 1].fuhao = 0;
				string = string + "-";
			}
			s1 = s1.substring(1);
			regex = "\\*";
			String[] arr2 = s1.split(regex);
			tempCoefficient = 1;
			pos = 0; // ���Դ洢��λ��
			for (String s2 : arr2) {
				// ����ÿ��
				String regex1 = "\\d+";
				if (s2.matches(regex1)) { // �������
					tempCoefficient = tempCoefficient * Integer.parseInt(s2);
				} else {
					// ��ĸ���
					flag = false; // �Ƿ���ֹ�
					tempN2 = 0;
					for (int i = 0; i <= pos; i++) { // ÿһ���������δ֪��
						if (s2.equals(n[tempN1].quantity[i])) {
							n[tempN1].index[tempN2]++;
							flag = true;
							break;
						}
						tempN2++;
					}
					if (!flag) {
						n[tempN1].quantity[pos] = s2;
						n[tempN1].index[pos] = 1;
						pos++;
					}
				}
			}

			// ÿһ��ϵ��
			n[tempN1].coefficient = tempCoefficient;

			// ׼�� �ַ���
			string = string + n[tempN1].coefficient;

			// ÿһ���ڲ���δ֪��
			for (int i3 = 0; !n[tempN1].quantity[i3].equals("1"); i3++) { // ������������
				for (int i4 = 0; i4 < n[tempN1].index[i3]; i4++) { // С�ڸô���
					string = string + "*" + n[tempN1].quantity[i3]; // �ӱ����ͳ˺�
				}
			}
			// ÿһ��ǰ��� + �� -
			tempN1++;
			tempCoefficient = 1;
		}
		// System.out.println(string);
		return string;
	} // 5*5+6*6*a+9*d*gg+9
}