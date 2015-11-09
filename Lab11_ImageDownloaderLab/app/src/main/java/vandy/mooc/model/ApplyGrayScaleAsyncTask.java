package vandy.mooc.model;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;

import vandy.mooc.common.BitmapUtils;
import vandy.mooc.presenter.ImagePresenter;

/**
 * Created by Antal János Monori on 09/11/15.
 * All rights reserved.
 */
public class ApplyGrayScaleAsyncTask extends AsyncTask<ImagePresenter, Void, ImagePresenter> {
    Uri originalImageUri;
    Uri directoryPathUri;
    Uri grayScaledPic;

    public ApplyGrayScaleAsyncTask(Uri originalImageUri, Uri directoryPathUri) {
        this.originalImageUri = originalImageUri;
        this.directoryPathUri =  directoryPathUri;
    }

    @Override
    protected ImagePresenter doInBackground(ImagePresenter...imagePresenters) {
        //Needed to handle false
        try{
            grayScaledPic = BitmapUtils.grayScaleFilter(imagePresenters[0].getActivityContext(), originalImageUri, directoryPathUri);

            File fdelete = new File(originalImageUri.getPath());
            if (fdelete.exists()) {
                if (fdelete.delete()) {
                    System.out.println("file Deleted :" + originalImageUri);
                } else {
                    System.out.println("file not Deleted :" + originalImageUri);
                }
            }

        }catch (Exception e){
            Log.d("Exception:", e.toString());
        }

        return imagePresenters[0];
    }

    @Override
    protected void onPostExecute(ImagePresenter imagePresenter) {
        //Sanity check, if the gray scaled pic was successfully converted and it exists, tell the presenter to process it
        if (grayScaledPic != null) imagePresenter.onProcessingComplete(grayScaledPic, directoryPathUri);
    }
}