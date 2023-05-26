package model.setup;

import model.tile.Tile;

public class TileSetup {
    
    final int MAX_TILE = 100;
    
    public Tile[] tiles;
    
    public TileSetup(){
        tiles = new Tile[MAX_TILE];
        int i = 0;
        
        // water
        tiles[i++] = new Tile("water", "water00");
        tiles[i++] = new Tile("water", "water01");
        
        tiles[i++] = new Tile("water", "water001");
        tiles[i++] = new Tile("water", "water011");
        tiles[i++] = new Tile("water", "water02");
        tiles[i++] = new Tile("water", "water03");
        tiles[i++] = new Tile("water", "water04");
        tiles[i++] = new Tile("water", "water05");
        tiles[i++] = new Tile("water", "water06");
        tiles[i++] = new Tile("water", "water07");
        tiles[i++] = new Tile("water", "water08");
        tiles[i++] = new Tile("water", "water09");
        tiles[i++] = new Tile("water", "water10");
        tiles[i++] = new Tile("water", "water11");
        tiles[i++] = new Tile("water", "water12");
        tiles[i++] = new Tile("water", "water13");
        
        // wall
        tiles[i++] = new Tile("wall", "wall00"); // 0
        
        // tree
        tiles[i++] = new Tile("tree", "tree00");
        tiles[i++] = new Tile("tree", "tree01");
        tiles[i++] = new Tile("tree", "tree02");
        tiles[i++] = new Tile("tree", "tree03");
        
        ///////////////
        
        // grass
        tiles[i++] = new Tile("grass", "grass00");
        tiles[i++] = new Tile("grass", "grass01");
        tiles[i++] = new Tile("grass", "grass02");
        tiles[i++] = new Tile("grass", "grass03");
        tiles[i++] = new Tile("grass", "grass04");
        tiles[i++] = new Tile("grass", "grass05");
        
        // earch
        tiles[i++] = new Tile("earth", "earth00");
        tiles[i++] = new Tile("earth", "earth01");
        tiles[i++] = new Tile("earth", "earth02");
        tiles[i++] = new Tile("earth", "earth03");
        
        // road
        tiles[i++] = new Tile("road", "road00");
        tiles[i++] = new Tile("road", "road01");
        tiles[i++] = new Tile("road", "road02");
        tiles[i++] = new Tile("road", "road03");
        tiles[i++] = new Tile("road", "road04");
        tiles[i++] = new Tile("road", "road05");
        tiles[i++] = new Tile("road", "road06");
        tiles[i++] = new Tile("road", "road07");
        tiles[i++] = new Tile("road", "road08");
        tiles[i++] = new Tile("road", "road09");
        tiles[i++] = new Tile("road", "road10");
        tiles[i++] = new Tile("road", "road11");
        tiles[i++] = new Tile("road", "road12");
        
        // bridge
        tiles[i++] = new Tile("bridge", "bridge01");
        tiles[i++] = new Tile("bridge", "bridge02");
        // 
        
    }
}
