public class J栈的应用括号匹配问题 {

    public static void main(String[] args) {
        String str = "(上海(长安)())";
        boolean match = isMatch(str);
        System.out.println(str + "中的括号是否匹配:" + match);
    }

    private static boolean isMatch(String str) {
        //1、创建栈对象，用来存储左括号
        Stack<String> chars = new Stack<>();

        //2、从左往右遍历字符串
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String currentChar = String.valueOf(c);


            //3、判断当前字符是否为左括号,如果是，把左括号放入栈中
            if (currentChar.equals("(")) {
                chars.push(currentChar);
            } else if (currentChar.equals(")")) {
            /*
              4、继续判断当前字符是否右括号，
              如果是，则从栈中弹出一个左括号，并判断弹出的结果是否为null，
              如果为null，没有匹配的左括号，如果不是null，则有匹配的右括号
             */
                String pop = chars.pop();
                if (pop == null) {
                    return false;
                }
            }
        }
        //5、判断栈中还有没有剩余的左括号，如果则证明括号不匹配
        if (chars.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
