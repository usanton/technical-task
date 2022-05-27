package service;

import model.CatImageRequest;

public final class CatImageService extends AbstractService<CatImageRequest> {

  private static CatImageService catImageService;

  private CatImageService() {
  }

  public static CatImageService getInstance() {
    if (catImageService == null) {
      catImageService = new CatImageService();
    }
    return catImageService;
  }

  @Override
  protected String uri() {
    return "/votes";
  }
}
