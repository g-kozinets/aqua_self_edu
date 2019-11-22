package project.forms;

import framework.pageElements.Button;
import framework.utils.FileUtils;
import org.openqa.selenium.By;

public class DownloadForm extends BaseForm{
    private Button downloadBtn = new Button(By.className("about_install_steam_link"), "Download install button");

    public DownloadForm() {
        uniqueElement = downloadBtn;
    }

    public void clickDownload() {
        downloadBtn.click();
        FileUtils.waitForFileDownload();
    }
}
