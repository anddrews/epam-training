package javaSE_2.src.by.gsu.epamlab.bll.readers.thread;

import javaSE_2.src.by.gsu.epamlab.bll.readers.ReaderBuffer;
import javaSE_2.src.by.gsu.epamlab.model.Constants.Constants;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;
import javaSE_2.src.by.gsu.epamlab.model.exeption.SourceException;
import javaSE_2.src.by.gsu.epamlab.model.xml.MySaxParserForThread;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;


public class ReaderXMLThread implements Runnable
{
        IFabricTest fileReader;
        ReaderBuffer readerBuffer;

        public ReaderXMLThread(IFabricTest fileReader, ReaderBuffer readerBuffer)
        {
            this.fileReader = fileReader;
            this.readerBuffer = readerBuffer;
        }

        @Override
        public void run()
        {

            try {
                XMLReader reader = XMLReaderFactory.createXMLReader();
                MySaxParserForThread handler = new MySaxParserForThread(fileReader,readerBuffer);

                reader.setContentHandler(handler);

                reader.parse(fileReader.getFileName());



            } catch (SAXException | IOException e)
            {
                System.err.println(Constants.FILE_NOT_FOUND);
            }
            finally
            {
                readerBuffer.endOfFile();
            }


        }
    }

