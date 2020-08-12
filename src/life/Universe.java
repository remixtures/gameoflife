package life;

class Universe {

    private int generations;
    private final int size;
    private Generation currentGeneration;

    public Universe(int size) {
        this.size = size;
        this.generations = 1;
        this.currentGeneration = new Generation(size);
    }

    public int advance() {
        this.currentGeneration = LifeAlgorithm.generateNextGeneration(currentGeneration);
        generations++;
        return generations;
    }

    public Generation getCurrentGeneration() {
        return currentGeneration;
    }

    public int getGenerations() {
        return generations;
    }
}