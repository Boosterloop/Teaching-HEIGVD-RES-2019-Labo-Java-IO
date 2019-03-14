package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collections;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {

      if(rootDirectory == null) {
          return;
      }

      // Do the visit
      vistor.visit(rootDirectory);

      // if directory -> store the directory's content in a list
      if(rootDirectory.isDirectory()) {
          LinkedList<File> fileList = new LinkedList<>();
          for (File file : rootDirectory.listFiles()) {
            fileList.add(file);
          }

          // if there is more than 1 element in list, sort
          if(fileList.size() > 1) {
              Collections.sort(fileList);
          }

          // Do the recursion for each file in list
          Iterator i = fileList.iterator();
          while (i.hasNext())
          {
              File f = (File)i.next();
              explore(f, vistor);
          }
      }
  }

}
