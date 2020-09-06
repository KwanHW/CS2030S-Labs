import java.util.Arrays;

public void serveCruises(Cruise[] cruises){
    Loader[] loaders = new Loader[0];

    for (Cruise c: cruises){
        // Determine number of loaders required to serve
        int loaderCount = c.getNumOfLoadersRequired();

        // If there are enough loaders
        for (int i=0; i<loaders.length; i++){
            Loader l = loaders[i];
            if (l.canServe(c)){
                loaders[i] = l.serve(c);
                System.out.println(loaders[i]);
                loaderCount--;

                if (loaderCount == 0)
                    break;
            }
        }
        
        // Checks if Cruise still require anymore loaders
        if (loaderCount > 0){
            // If there are insufficent loaders, new loaders must be generated
            for (int i=0; i<loaderCount; i++){
                // Copies the existing loader to a new loader with 1 extra space
                loaders = Arrays.copyOf(loaders, loaders.length+1);
                int lastIndex = loaders.length -1;

                // Creates the new loader
                // Every 3rd loader is a RecycledLoader
                if (((loaders.length % 3) == 0) && (loaders.length >= 3)) {
                    loaders[lastIndex] = new RecycledLoader(loaders.length, c);
                } else {
                    loaders[lastIndex] = new Loader(loaders.length, c); 
                }
                loaders[lastIndex] = loaders[lastIndex].serve(c);
                System.out.println(loaders[lastIndex]);
            }
        }
    }
}
