public class SearchTree {
	
	int wert;
	SearchTree lc;
	SearchTree rc;	

	public SearchTree(int wert) {
		this.wert = wert;
	}

	public SearchTree(int[] werte) {
		if(werte.length>0) {
			wert = werte[0];
			for ( int i = 1 ; i<werte.length ; i++ ) {
				this.add(werte[i]);
			}
		}
	}

	public void add(int next) {
		if(next < wert) {
			if(lc == null)  {
				lc = new SearchTree(next);
			} else {
				lc.add(next);
			}
		} else if (next > wert) {
			if(rc == null)  {
				rc = new SearchTree(next);
			} else {
				rc.add(next);
			}
		}
	}

	public void inOrder() {
		if(lc != null) {
			lc.inOrder();
		}
		System.out.print(" " + wert + ",");
		if(rc != null) {
			rc.inOrder();
		}
	}

	public void preOrder() {
		System.out.print(" " + wert + ",");
		if(lc != null) {
			lc.preOrder();
		}
		if(rc != null) {
			rc.preOrder();
		}
	}

	public void postOrder() {
		if(lc != null) {
			lc.postOrder();
		}
		if(rc != null) {
			rc.postOrder();
		}
		System.out.print(" " + wert + ",");
		
	}		
	@Override
	public String toString() {
		return " " + wert + " ";
	}
}