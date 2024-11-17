package ten40EZ;
class Main {
  public static void main(String[] args) {
    Checking1040EZ checker = new Checking1040EZ(args);

    int[] lines = new int[14];

    lines[0] = checker.getLine1("1 - Wages, salaries, and tips");
    lines[1] = checker.getLine2("2 - Taxable interest. If the total is over $1,500, you cannot use Form 1040EZ.");
    lines[2] = checker.getLine3("3 - Unemployment compensation and Alaska Permanent Fund dividends.");
    lines[3] = checker.calculateLine4(lines[0], lines[1], lines[2]);
    lines[4] = checker.calculateLine5(
            lines[0],
            checker.getLine5A("5 - If someone can claim you as a dependent, enter \"1\". Else, enter a \"0\"")
    );
    lines[5] = checker.calculateLine6(lines[3], lines[4]);
    lines[6] = checker.getLine7("7 - Federal income tax withheld from Form(s) W-2 and 1099.");
    lines[7] = 0;
    lines[8] = checker.calculateLine9(lines[6], lines[7]);
    lines[9] = checker.getLine10("10 - Tax. Use the amount on line 6 above to find your tax in the tax table in the " +
            "instructions. Then, enter the tax from the table on this line.", lines[5]);
    lines[10] = 0;
    lines[11] = checker.calculateLine12(lines[9], lines[10]);
    lines[12] = checker.calculateLine13(lines[8], lines[11]);
    lines[13] = checker.calculateLine14(lines[8], lines[11]);

    for (int i = 0; i < lines.length; i++) {
      System.out.print("Line " + (i + 1) + ":");
      System.out.print(" ".repeat(5 - Integer.toString(i).length()));
      System.out.println(lines[i]);
    }
  }
}