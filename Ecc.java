import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Ecc{
	int a,b,q;
	HashMap<Pair,Pair> pt=new HashMap<>();
	HashMap<Pair,Pair> subgrouppt=new HashMap<>();
	Ecc(int a,int b,int q){
		this.a=a;
		this.b=b;
		this.q=q;
	}
	
	
	void EccPointGen() {
		int x=0;
		while(x<q) {
			int xc=(int)Math.pow(x, 3);
			int w=(xc+(a*x)+b)%q;
			int wr=find_sq(w);
			if(wr!=Integer.MAX_VALUE && !(pt.containsKey(new Pair(x,wr)) || pt.containsKey(new Pair(x,(q-wr)%q))) ) {
				pt.put(new Pair(x,wr),new Pair(x,(q-wr)%q));
			}
			x++;
		}
	}
	
	
	int find_sq(int w) {
		
		for(int i=0;i<q;i++) {
			if((i*i)%q==w) {
				return i;
			}
		}
		return Integer.MAX_VALUE;
	}
	
	
	void PrintMap() {
		for(Entry<Pair, Pair> e:pt.entrySet()) {
			System.out.println("{"+e.getKey().x+" , "+e.getKey().y+"}  "+"{"+e.getValue().x+" , "+e.getValue().y+"}  ");
		}
	}
	
	
	Pair findBasePoint() {
		Pair p=new Pair(Integer.MAX_VALUE,Integer.MAX_VALUE);
		int n=(pt.size()*2) + 1;
		int sgSize;
		if(! isPrime(n)) {
			ArrayList<Integer>factors=new ArrayList<>();
			generatePrimeFactors(n,factors);
			sgSize=findMax(factors);
			for(Entry<Pair,Pair> e : pt.entrySet()) {
				p=e.getKey();
				Pair d=e.getKey();
				subgrouppt.put(p,pt.get(p));
				for(int i=1;i<sgSize;i++) {
					d=add(p,d);
					if(!(subgrouppt.containsKey(d) || subgrouppt.containsKey(new Pair(d.x,(q-d.y)%q)))) {
						subgrouppt.put(d, new Pair(d.x,(q-d.y)%q));
					}
				}
				if(subgrouppt.size()==sgSize) {
					return p;
				}
				subgrouppt.clear();
			}
		}
		else {
			
			for(Entry<Pair,Pair> e:pt.entrySet()) {
				return e.getKey();
			}
		}
		return p;
	}
	
	
	Pair add(Pair p,Pair d) {
		if(p.x==d.x) {
			if(p.y==0) {
				p.y=q-p.y;
			}
			
			int lmbda= ((3*((int)Math.pow(p.x, 2)) + a)/(2*p.y));
			lmbda=lmbda % q;
			if(lmbda<0) {
				lmbda=findPos(lmbda);
			}
			int x3= ((int)Math.pow(lmbda, 2)) - p.x -d.x;
			x3=x3 % q;
			if(x3<0) {
				x3=findPos(x3);
			}
			int y3= (lmbda * (p.x - x3)) - p.y;
			y3=y3 % q;
			if(y3 < 0) {
				y3 =findPos(y3);
			}
			return new Pair(x3,y3);
		}
		else {
			int lmbda=(d.y-p.y)/(d.x - p.x);
			lmbda=lmbda % q;
			if(lmbda<0) {
				lmbda=findPos(lmbda);
			}
			int x3= ((int)Math.pow(lmbda, 2)) - p.x -d.x;
			x3=x3 % q;
			if(x3<0) {
				x3=findPos(x3);
			}
			int y3= (lmbda * (p.x - x3)) - p.y;
			y3=y3 % q;
			if(y3 < 0) {
				y3 =findPos(y3);
			}
			return new Pair(x3,y3);
			
		}
	}
	
	int findPos(int n) {
		while(n<0) {
			n +=q;
		}
		return n;
	}
	
	
	int findMax(ArrayList<Integer> factors) {
		int max=Integer.MIN_VALUE;
		for(int i=0;i<factors.size();i++) {
			max=Math.max(max, factors.get(i));
		}
		return max;
	}
	
	
	void generatePrimeFactors(int n,ArrayList<Integer> factors){
		 int c = 2;
	        while (n > 1) {
	            if (n % c == 0) {
	            	if(!factors.contains(c))
	                factors.add(c);
	                n /= c;
	            }
	            else
	                c++;
	        }
	}
	
	
	boolean isPrime(int n)
    {
 
        if (n <= 1)
            return false; 
        else if (n == 2)
            return true;       
        else if (n % 2 == 0)
            return false;       
        for (int i = 3; i <= Math.sqrt(n); i += 2)
        {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
class Pair{
	int x,y;
	Pair(int x,int y){
		this.x=x;
		this.y=y;
	}
}
