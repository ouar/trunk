package fr.gfi.android.quiz.flashcode.process;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.FrameLayout;
import fr.gfi.android.quiz.R;
import fr.gfi.android.quiz.activities.AbstractActivityOrientationPortrait;
import fr.gfi.android.quiz.helpers.InternetConnectivity;
import fr.gfi.android.quiz.helpers.Messages;
import fr.gfi.android.quiz.model.InfosFromFlashCode;
import fr.gfi.android.quiz.webservices.TacheAppelsReseaux;




public class FlashCodeProcessActivity extends AbstractActivityOrientationPortrait
{
    private Camera mCamera;
    private CameraPreview mPreview;
    private Handler autoFocusHandler;
    private TacheAppelsReseaux flashcodeContainer= null ;
    
    ImageScanner scanner;

    private boolean barcodeScanned = false;
    private boolean previewing = true;

    static {
        System.loadLibrary("iconv");
    } 

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.flashcode_view);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        autoFocusHandler = new Handler();
        mCamera = getCameraInstance();
        
        /* Instance barcode scanner */
        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);

        mPreview = new CameraPreview(this, mCamera, previewCb, autoFocusCB);
        FrameLayout preview = (FrameLayout)findViewById(R.id.cameraPreview);
        preview.addView(mPreview);

        this.scanProcess();
    }

    @Override
    protected void onDestroy()
    {
        Log.v("MediaVideo", "onDestroy");
        super.onDestroy();
        TacheAppelsReseaux.dialog.dismiss();
        releaseCamera();
        
    }
    
    public void onPause() {
        super.onPause();
       
        TacheAppelsReseaux.dialog.dismiss();
        releaseCamera();
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e){
        }
        return c;
    }

    private void releaseCamera() {
        if (mCamera != null) {
            previewing = false;
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private Runnable doAutoFocus = new Runnable() {
            public void run() {
                if (previewing)
                    mCamera.autoFocus(autoFocusCB);
            }
        };

    PreviewCallback previewCb = new PreviewCallback() {
            public void onPreviewFrame(byte[] data, Camera camera) {
                Camera.Parameters parameters = camera.getParameters();
                Size size = parameters.getPreviewSize();

                Image barcode = new Image(size.width, size.height, "Y800");
                barcode.setData(data);

                int result = scanner.scanImage(barcode);
                
                if (result != 0) {
                    previewing = false;
                    mCamera.setPreviewCallback(null);
                    mCamera.stopPreview();
                    
                    SymbolSet syms = scanner.getResults();
                    for (Symbol sym : syms) {
                        Log.i("SCAN SUCCEED" , "barcode result " + sym.getData());
                        barcodeScanned = true;
                        
                        if(InternetConnectivity.haveInternet(FlashCodeProcessActivity.this))
                        {
                        	InfosFromFlashCode infos;
                        	try{
                        		infos= new InfosFromFlashCode(sym.getData());
                        	}catch(Exception e){
                        		e.printStackTrace();
                        		return;
                        	}
                        	 
                        	
                        	flashcodeContainer = new TacheAppelsReseaux();
                            TacheAppelsReseaux.applicationContext= FlashCodeProcessActivity.this;
                            flashcodeContainer.url_to_parse = infos.getsBaseUrl() + "/getjson/" + infos.getiIdQuiz();
                            flashcodeContainer.execute();
                        }else
                        {
                        	// no internet connection
                        	Messages.internetConnectionFaliedMessage(FlashCodeProcessActivity.this);
                        	
                        }
                        
                    }
                }else
                {
                	// no code bar found 
                }
            }
        };

    // Mimic continuous auto-focusing
    AutoFocusCallback autoFocusCB = new AutoFocusCallback() {
            public void onAutoFocus(boolean success, Camera camera) {
                autoFocusHandler.postDelayed(doAutoFocus, 1000);
            }
        };
        
     private void scanProcess()
     {
    	 Log.i("barcodeScanned",""+  barcodeScanned);
    	 if (barcodeScanned) {
             barcodeScanned = false;
             mCamera.setPreviewCallback(previewCb);
             mCamera.startPreview();
             previewing = true;
             mCamera.autoFocus(autoFocusCB);
             Log.i("SCAN", "NOK");
         }else
         {
        	 Log.i("SCAN", "OK");
         }
     }
     
     public static void remmoveFromSuperView(Context context)
     {
    	 ((Activity) context).finish();
     }
}
