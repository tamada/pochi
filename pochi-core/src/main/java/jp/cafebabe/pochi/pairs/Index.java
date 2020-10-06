package jp.cafebabe.pochi.pairs;

class Index{
    private int current;

    public Index(int index){
        this.current = index;
    }

    public int index(){
        int i = current;
        current++;
        return i;
    }
}
