package br.org.funcate.glue.os.enumeration;

public enum Constants {
        OPENED(1),
        CLOSED(2),
        CRASH(3);    
        private int value;

		private Constants(int value) {
			this.value = value;	
		}
		
		public int value(){
			return value;
		}   
}


