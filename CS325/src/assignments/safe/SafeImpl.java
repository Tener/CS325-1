package assignments.safe;

public class SafeImpl implements Safe {


	private char[] display = new char[Safe.DISPLAY_LENGTH];
	private int index = 0;
	private boolean locked = true;
	private boolean kPressed = false;
	
	public SafeImpl() {
		display = Safe.BLANK_DISPLAY.toCharArray();
	}

	@Override
	public boolean isLocked() {
		return locked;
	}

	@Override
	public Object readDisplay() {
		return String.valueOf(display);
	}

	@Override
	public void enter(char c) {
		if (!kPressed){
			kPressed  = (c == 'K') ;
		}
		
		if(Character.isDigit(c) && !kPressed){
			display = Safe.ERROR_DISPLAY.toCharArray();
			return;
		}
		
		if (Character.isDigit(c)){
			display [index++] = c;
		}
		if (kPressed && String.valueOf(display).equals("123456")){
			locked = false;
			kPressed = false;
			display = Safe.OPEN_DISPLAY.toCharArray();
		}
	}

}
