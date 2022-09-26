import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class Setup {
	private ArrayList<Integer>PP=new ArrayList<>();
	private int a,b,q;
	Ecc ec;
	private Pair bp,PPKgc,PPCs;
	private int KgcMk,CsMk;
	Setup(int a,int b, int q){
		this.a=a;
		this.b=b;
		this.q=q;
		this.ec=new Ecc(a,b,q);
		ec.EccPointGen();
		bp =ec.findBasePoint();
		ec.PrintMap();
		System.out.println(" Base Point : "+bp.x + " , "+ bp.y);
		
	}
	
	
	void AuthenticateAttr(ArrayList<String>Attributes) {
		for(int i=0;i<Attributes.size();i++) {
			PP.add(i+1);
		}
	}
	
	
	void KGCSetup(Ecc ec) {
//		int max=q-1,min=1;
//		int range=max-min+1;
		do {
//		KgcMk=((int)(Math.random()*range))+min;
			Random rand=new Random();
			int KgcMk_index=rand.nextInt(ec.randomVal.size());
			KgcMk=ec.randomVal.get(KgcMk_index);
			
			System.out.println("KGCMK"+KgcMk);
		PPKgc=bp;
		for(int i=2;i<=KgcMk;i++) {
			PPKgc=ec.add(bp, PPKgc);
		}
		System.out.println(PPKgc.x + " "+PPKgc.y);
	}while((ec.pt.containsKey(PPKgc) || ec.pt.containsValue(PPKgc)));
		
		System.out.println("KGC SETUP COMPLETED");
	}
	
	void CsSetup(Ecc ec) {
//		int max=q-1,min=1;
//		int range=max-min+1;
//		do {
////		CsMk=((int)(Math.random()*range))+min;
//			Random rand=new Random();
//			int CsMk_index=rand.nextInt(ec.randomVal.size());
//			CsMk=ec.randomVal.get(CsMk_index);
//			System.out.println("CSMK"+CsMk);
////		while(CsMk==KgcMk) {
////			CsMk=rand.nextInt(q-1)+1;
////		}
//		PPCs=bp;
//		for(int i=2;i<=CsMk;i++) {
//			PPCs=ec.add(bp, PPCs);
//		}
//		}while(!(ec.pt.containsKey(PPCs) || ec.pt.containsValue(PPCs)));
//		
		System.out.println("CS SETUP COMPLETED");
	}
	
	ArrayList<Object> getParam() {
		ArrayList<Object> param=new ArrayList<Object>();
		param.add(PP);
		param.add(PPKgc);
		param.add(PPCs);
		return param;
	}
}

