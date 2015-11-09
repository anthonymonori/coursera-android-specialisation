package vandy.mooc.model;

import android.net.Uri;
import android.os.AsyncTask;

import vandy.mooc.presenter.ImagePresenter;

/**
 * Created by Antal János Monori on 09/11/15.
 * All rights reserved.
 */
public class ImageDownloaderAsyncTask extends AsyncTask<ImagePresenter, Void, ImagePresenter> {

    Uri uri;
    Uri directoryPathUri;
    Uri savedUri;

    public ImageDownloaderAsyncTask(Uri uri, Uri directoryPathUri) {
        this.uri = uri;
        this.directoryPathUri = directoryPathUri;
    }

    @Override
    protected ImagePresenter doInBackground(ImagePresenter... imagePresenters) {

        savedUri = new ImageDownloadsModel().downloadImage(imagePresenters[0].getActivityContext(), uri, directoryPathUri);

        return imagePresenters[0];
    }

    @Override
    protected void onPostExecute(ImagePresenter imagePresenter) {
        new ApplyGrayScaleAsyncTask(savedUri, directoryPathUri).execute(imagePresenter);
    }
}
