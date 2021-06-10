package com.example.sli;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import org.tensorflow.lite.Interpreter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Classifier {
    private Interpreter interpreter;
    private List<String> labellist;
    private int INPUT_SIZE;
    private int PIXEL_SIZE=3;
    private int IMAGE_MEAN=0;
    private float IMAGE_STD=255.0f;
    private float MAX_RESULTS=3;
    private float THRESHOLD=0.4f;
    Classifier(AssetManager assetManager,String modelPath,String labelPath,int inputSize)throws IOException{
        INPUT_SIZE=inputSize;
        Interpreter.Options options=new Interpreter.Options();
        options.setNumThreads(5);
        options.setUseNNAPI(true);
        interpreter = new Interpreter(loadModelFile(assetManager,modelPath),options);
        labellist=loadLabelList(assetManager,labelPath);
    }

    class Recognition{
        String id="";
        String title="";
        String confidence;

        public Recognition(String i, String S, float confidence){
            id=i;
            title=S;
            confidence=confidence;
        }

        @Nullable
        @Override
        public String toString(){
            return "Title="+title+"";
        }

        private MappedByteBuffer loadModelFile(AssetManager assetManager,String Model_File)throws IOException{
            AssetFileDescriptor fileDescriptor=assetManager.openFd(Model_File);
            FileInputStream inputStream= new FileInputStream(fileDescriptor.getFileDescriptor());
            FileChannel fileChannel=inputStream.getChannel();
            long startoffset=fileDescriptor.getStartOffset();
            long decalredlength=fileDescriptor.getDeclaredLength();
            return fileChannel.map(FileChannel.MapMode.READ_ONLY,startoffset,decalredlength);
        }

        private List<String> loadLabelList(AssetManager assetManager,String labelpath) throws IOException{
            List<String> labelList=new ArrayList<>();
            BufferedReader reader=new BufferedReader(new InputStreamReader(assetManager.open(labelpath)));
            String line;
            while ((line=reader.readLine())!=null){
                labelList.add(line);
            }
            reader.close();
            return labelList;
        }
        List<Recognition>=recognizeImage(Bitmap bitmap){
        Bitmap scaledBitmap=Bitmap.createScaledBitmap(bitmap,INPUT_SIZE,INPUT_SIZE,false);
        ByteBuffer byteBuffer= convertBitmaptoByteBuffer(scaledBitmap);
        float [][] result=new float[1][labellist.size()];
        interpreter.run(byteBuffer,result);
        return getSortedResultFloat(result);
        }

        private ByteBuffer convertBitmaptoByteBuffer(Bitmap bitmap){
            ByteBuffer byteBuffer;
            byteBuffer=ByteBuffer.allocateDirect(4*INPUT_SIZE*INPUT_SIZE*PIXEL_SIZE);
            byteBuffer.order(ByteOrder.nativeOrder());
            int[] intValues=new int[INPUT_SIZE*INPUT_SIZE];
            bitmap.getPixels(intValues,0,bitmap.getWidth(),0,0,bitmap.getWidth(),bitmap.getHeight());
            int pixel=0;
            for(int i=0;i<INPUT_SIZE;++i){
                for(int j=0;j<INPUT_SIZE;++j){
                    final int val=intValues[pixel++];

                    byteBuffer.putFloat((((val>>16)& 0xFF)-IMAGE_MEAN)/IMAGE_STD);
                    byteBuffer.putFloat((((val>>8)& 0xFF)-IMAGE_MEAN)/IMAGE_STD);
                    byteBuffer.putFloat((((val)& 0xFF)-IMAGE_MEAN)/IMAGE_STD);

                }

            }
            return byteBuffer;
        }

        @SuppressLint("DefaultLocale")
        private List<Recognition> getSortedResultFloat(float[][] labelProbArray){
            PriorityQueue<Recognition> pq=new PriorityQueue<>(
                    (int) MAX_RESULTS,
                    (Comparator)(lhs,rhs)->{
                        return Float.compare(rhs.confidence,lhs.confidence);
                    });
            for(int i=0;i<labellist.size();++i){
                float confidence=labelProbArray[0][i];
                if ( confidence>THRESHOLD)
                    pq.add(new Recognition("" + i, labellist.size() > i ? labellist.get(i) : "unknown", confidence));
            }
            final ArrayList<Recognition> recognitions=new ArrayList<>();
            int recognitionssize=(int) Math.min(pq.size(), MAX_RESULTS);
            for(int i=0;i<recognitionssize;++i){
                recognitions.add(pq.poll());
            }
            return recognitions;
        }



   }}





