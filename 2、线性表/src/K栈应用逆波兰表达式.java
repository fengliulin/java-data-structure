public class K栈应用逆波兰表达式 {
    public static void main(String[] args) {

        //中缀表达式 3*（17-15）+18/6 的逆波兰表达式如下 6+3=9
        String[] notation = {"3", "17", "15", "-", "*", "18", "6", "/", "+"};
        int result = caculate(notation);
        System.out.println("逆波兰表达式的结果为：" + result);
    }

    private static int caculate(String[] notation) {
        //1、定一个栈，用来存储操作数
        Stack<Integer> operand = new Stack<>();

        //2、从左往右遍历逆波兰表达式，得到每一个元素
        for (int i = 0; i < notation.length; i++) {
            String current = notation[i];
            Integer o1;
            Integer o2;
            Integer result;
            //3、判断当前元素是运算符还是操作数
            switch (current) {
                case "+":
                    //4、运算符，从栈中弹出两个操作数，完成运算，运算完的结果在压入到栈中
                    o1 = operand.pop();
                    o2 = operand.pop();
                    result = o2 + o1;
                    operand.push(result);
                    break;
                case "-":
                    o1 = operand.pop();
                    o2 = operand.pop();
                    result = o2 - o1;
                    operand.push(result);
                    break;
                case "*":
                    o1 = operand.pop();
                    o2 = operand.pop();
                    result = o2 * o1;
                    operand.push(result);
                    break;
                case "/":
                    o1 = operand.pop();
                    o2 = operand.pop();
                    result = o2 / o1;
                    operand.push(result);
                    break;
                default:
                    //5、操作数，把该操作数放入到栈中
                    operand.push(Integer.parseInt(current));
                    break;
            }
        }


        //6、得到栈中最后一个元素，就是逆波兰表达式的结果
        int result = operand.pop();
        return result;

    }
}
