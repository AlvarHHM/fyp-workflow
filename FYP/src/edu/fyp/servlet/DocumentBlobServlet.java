package edu.fyp.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import edu.fyp.bean.DocumentBlob;
import edu.fyp.repository.PMF;

public class DocumentBlobServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// Get the image representation
		try {
			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator iter = upload.getItemIterator(req);
			FileItemStream imageItem = iter.next();
			InputStream imgStream = imageItem.openStream();

			// construct our entity objects
			Blob document = new Blob(IOUtils.toByteArray(imgStream));
			DocumentBlob docuementBlob = new DocumentBlob(imageItem.getName(),
					document);

			// persist image
			PersistenceManager pm = PMF.get().getPersistenceManager();
			pm.makePersistent(docuementBlob);
			pm.close();

			// respond to query
			res.setContentType("text/plain");
			res.getWriter()
					.write(KeyFactory.keyToString(docuementBlob.getId()));
		} catch (Exception e) {
			e.printStackTrace(res.getWriter());
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String skey = req.getParameter("id");
		resp.setContentType("application/octet-stream");
		
		Key key = KeyFactory.stringToKey(skey);
		PersistenceManager pm = PMF.get().getPersistenceManager();;
		DocumentBlob document = pm.getObjectById(DocumentBlob.class, key);
				
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + document.getName() + "\"");
		resp.getOutputStream().write(document.getDocuement().getBytes());

	}

}
