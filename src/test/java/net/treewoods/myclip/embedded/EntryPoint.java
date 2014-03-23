/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.myclip.embedded;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.api.ActionReport;
import org.glassfish.api.admin.ParameterMap;
import org.glassfish.embeddable.CommandRunner;
import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;
import org.glassfish.embeddable.archive.ScatteredArchive;
import org.glassfish.internal.embedded.ContainerBuilder;
import org.glassfish.internal.embedded.EmbeddedDeployer;
import org.glassfish.internal.embedded.EmbeddedFileSystem;
import org.glassfish.internal.embedded.LifecycleException;
import org.glassfish.internal.embedded.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author kido
 */
public class EntryPoint {

    @BeforeClass
    public static void setUpClass() {
        try {
            final String CONTEXT_NAME = "myclip";
            final String WEB_ROOT = "target/MyClip-1.0.0";

            //環境設定
            GlassFishProperties props = new GlassFishProperties();
            props.setPort("http-listener", 8080);
            //GlassfishServer起動
            long start = System.nanoTime();
            GlassFish glassFish = GlassFishRuntime.bootstrap().newGlassFish(props);
            glassFish.start();
            long end = System.nanoTime();
            System.out.printf("起動時間：%,dms%n", (end - start) / 1000 / 1000);

            // コネクションプール、データソース追加
            CommandRunner commandRunner = glassFish.getCommandRunner();
            File resourceFile = new File("src/test/setup/glassfish-resources.xml");
            commandRunner.run("add-resources", resourceFile.getPath());

            //デプロイ
            File webRoot = new File(WEB_ROOT);//Webコンテンツのルート
            //File classRoot = new File("target/classes");//クラスパスルート

            Deployer deployer = glassFish.getDeployer();
            ScatteredArchive archive = new ScatteredArchive(CONTEXT_NAME, ScatteredArchive.Type.WAR, webRoot);
            //archive.addClassPath(classRoot);//クラスパス追加
            //archive.addClassPath(new File("hoge.jar"));//適当なライブラリがあればjarも追加可能
            deployer.deploy(archive.toURI());

            //ブラウザ起動
            Desktop.getDesktop().browse(new URI("http://localhost:8080/" + CONTEXT_NAME + "/"));

            //コンソールでEnterおすまでブロック
            Scanner scanner = new Scanner(System.in);
            scanner.hasNextLine();

            //停止
            glassFish.stop();
        }
        catch (IOException | GlassFishException | URISyntaxException ex) {
            Logger.getLogger(EntryPoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testSelectAll() {
        System.out.println("selectAll");

    }
}
