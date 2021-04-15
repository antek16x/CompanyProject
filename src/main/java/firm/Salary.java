package firm;

public class Salary implements Comparable<Salary> {
    private int zlote;
    private int grosze;

    public Salary(){}

    public Salary(int zlote, int grosze){
        if(zlote>=0 && grosze >=0) {
            this.zlote = zlote;
            this.grosze = grosze;

        }else
            throw new IllegalArgumentException();
    }

    public Salary(Salary kwota){
        this.zlote = kwota.zlote;
        this.grosze = kwota.grosze;
    }

    public int getZlote() {
        return zlote;
    }

    public int getGrosze() {
        return grosze;
    }

    public Salary add(Salary kwota){

        int zl = this.zlote + kwota.zlote;
        int gr = this.grosze + kwota.grosze;
        if (gr>=100){
            zl++;
            gr = gr - 100;
        }
        Salary wynik = new Salary(zl, gr);
        return wynik;
    }

    public Salary subtract(Salary kwota) {
        int gr1 = this.zlote*100 + this.grosze;
        int gr2 = kwota.zlote*100 + kwota.grosze;
        if (gr1 >= gr2){
            gr1= gr1-gr2;
            return new Salary(gr1/100, gr1%100);
        }
        else throw new IllegalArgumentException("nie można wykonać odejmowania");
    }

    public Salary multiply(int i){
        if(i<0){
            throw new IllegalArgumentException();
        }else{
            int wynik = (this.zlote * 100 + this.grosze) * i;
            return new Salary(wynik/100, wynik%100);
        }
    }

    public String toString(){
        if (grosze>9)
            return zlote+","+grosze;
        else
            return zlote + ",0"+grosze;
    }


    public int compareTo(Salary o) {
        int groszeThis = this.grosze + this.zlote * 100;
        int groszeO = o.grosze + o.zlote * 100;
        if (groszeThis < groszeO) return -1;
        if (groszeThis == groszeO) return 0;
        return 1;
    }
}