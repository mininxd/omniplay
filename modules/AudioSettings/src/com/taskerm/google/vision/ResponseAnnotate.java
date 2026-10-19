package com.joaomgcd.taskerm.google.vision;

import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class ResponseAnnotate {
    private final Response[] responses;

    /* JADX WARN: Multi-variable type inference failed */
    public ResponseAnnotate() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ResponseAnnotate(Response[] responseArr) {
        this.responses = responseArr;
    }

    public /* synthetic */ ResponseAnnotate(Response[] responseArr, int i, g gVar) {
        this((i & 1) != 0 ? (Response[]) null : responseArr);
    }

    public final Response[] getResponses() {
        return this.responses;
    }

    @Keep
    public static final class Response {
        private final CropHintsAnnotation cropHintsAnnotation;
        private final FaceAnnotation[] faceAnnotations;
        private final ImageProperties imagePropertiesAnnotation;
        private final EntityAnnotation[] labelAnnotations;
        private final EntityAnnotation[] landmarkAnnotations;
        private final LocalizedObjectAnnotation[] localizedObjectAnnotations;
        private final EntityAnnotation[] logoAnnotations;
        private final SafeSearchAnnotation safeSearchAnnotation;
        private final EntityAnnotation[] textAnnotations;
        private final WebDetection webDetection;

        public Response() {
            this(null, null, null, null, null, null, null, null, null, null, 1023, null);
        }

        public Response(FaceAnnotation[] faceAnnotationArr, EntityAnnotation[] entityAnnotationArr, EntityAnnotation[] entityAnnotationArr2, EntityAnnotation[] entityAnnotationArr3, EntityAnnotation[] entityAnnotationArr4, LocalizedObjectAnnotation[] localizedObjectAnnotationArr, ImageProperties imageProperties, SafeSearchAnnotation safeSearchAnnotation, CropHintsAnnotation cropHintsAnnotation, WebDetection webDetection) {
            this.faceAnnotations = faceAnnotationArr;
            this.logoAnnotations = entityAnnotationArr;
            this.labelAnnotations = entityAnnotationArr2;
            this.landmarkAnnotations = entityAnnotationArr3;
            this.textAnnotations = entityAnnotationArr4;
            this.localizedObjectAnnotations = localizedObjectAnnotationArr;
            this.imagePropertiesAnnotation = imageProperties;
            this.safeSearchAnnotation = safeSearchAnnotation;
            this.cropHintsAnnotation = cropHintsAnnotation;
            this.webDetection = webDetection;
        }

        public /* synthetic */ Response(FaceAnnotation[] faceAnnotationArr, EntityAnnotation[] entityAnnotationArr, EntityAnnotation[] entityAnnotationArr2, EntityAnnotation[] entityAnnotationArr3, EntityAnnotation[] entityAnnotationArr4, LocalizedObjectAnnotation[] localizedObjectAnnotationArr, ImageProperties imageProperties, SafeSearchAnnotation safeSearchAnnotation, CropHintsAnnotation cropHintsAnnotation, WebDetection webDetection, int i, g gVar) {
            this((i & 1) != 0 ? (FaceAnnotation[]) null : faceAnnotationArr, (i & 2) != 0 ? (EntityAnnotation[]) null : entityAnnotationArr, (i & 4) != 0 ? (EntityAnnotation[]) null : entityAnnotationArr2, (i & 8) != 0 ? (EntityAnnotation[]) null : entityAnnotationArr3, (i & 16) != 0 ? (EntityAnnotation[]) null : entityAnnotationArr4, (i & 32) != 0 ? (LocalizedObjectAnnotation[]) null : localizedObjectAnnotationArr, (i & 64) != 0 ? (ImageProperties) null : imageProperties, (i & 128) != 0 ? (SafeSearchAnnotation) null : safeSearchAnnotation, (i & 256) != 0 ? (CropHintsAnnotation) null : cropHintsAnnotation, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (WebDetection) null : webDetection);
        }

        public final FaceAnnotation[] getFaceAnnotations() {
            return this.faceAnnotations;
        }

        public final EntityAnnotation[] getLogoAnnotations() {
            return this.logoAnnotations;
        }

        public final EntityAnnotation[] getLabelAnnotations() {
            return this.labelAnnotations;
        }

        public final EntityAnnotation[] getLandmarkAnnotations() {
            return this.landmarkAnnotations;
        }

        public final EntityAnnotation[] getTextAnnotations() {
            return this.textAnnotations;
        }

        public final LocalizedObjectAnnotation[] getLocalizedObjectAnnotations() {
            return this.localizedObjectAnnotations;
        }

        public final ImageProperties getImagePropertiesAnnotation() {
            return this.imagePropertiesAnnotation;
        }

        public final SafeSearchAnnotation getSafeSearchAnnotation() {
            return this.safeSearchAnnotation;
        }

        public final CropHintsAnnotation getCropHintsAnnotation() {
            return this.cropHintsAnnotation;
        }

        public final WebDetection getWebDetection() {
            return this.webDetection;
        }
    }

    @Keep
    public static final class WebDetection {
        private final WebLabel[] bestGuessLabels;
        private final WebPage.WebImage[] fullMatchingImages;
        private final WebPage[] pagesWithMatchingImages;
        private final WebPage.WebImage[] partialMatchingImages;
        private final WebPage.WebImage[] visuallySimilarImages;
        private final WebEntity[] webEntities;

        public WebDetection() {
            this(null, null, null, null, null, null, 63, null);
        }

        public WebDetection(WebPage[] webPageArr, WebPage.WebImage[] webImageArr, WebPage.WebImage[] webImageArr2, WebLabel[] webLabelArr, WebPage.WebImage[] webImageArr3, WebEntity[] webEntityArr) {
            this.pagesWithMatchingImages = webPageArr;
            this.partialMatchingImages = webImageArr;
            this.visuallySimilarImages = webImageArr2;
            this.bestGuessLabels = webLabelArr;
            this.fullMatchingImages = webImageArr3;
            this.webEntities = webEntityArr;
        }

        public /* synthetic */ WebDetection(WebPage[] webPageArr, WebPage.WebImage[] webImageArr, WebPage.WebImage[] webImageArr2, WebLabel[] webLabelArr, WebPage.WebImage[] webImageArr3, WebEntity[] webEntityArr, int i, g gVar) {
            this((i & 1) != 0 ? (WebPage[]) null : webPageArr, (i & 2) != 0 ? (WebPage.WebImage[]) null : webImageArr, (i & 4) != 0 ? (WebPage.WebImage[]) null : webImageArr2, (i & 8) != 0 ? (WebLabel[]) null : webLabelArr, (i & 16) != 0 ? (WebPage.WebImage[]) null : webImageArr3, (i & 32) != 0 ? (WebEntity[]) null : webEntityArr);
        }

        public final WebPage[] getPagesWithMatchingImages() {
            return this.pagesWithMatchingImages;
        }

        public final WebPage.WebImage[] getPartialMatchingImages() {
            return this.partialMatchingImages;
        }

        public final WebPage.WebImage[] getVisuallySimilarImages() {
            return this.visuallySimilarImages;
        }

        public final WebLabel[] getBestGuessLabels() {
            return this.bestGuessLabels;
        }

        public final WebPage.WebImage[] getFullMatchingImages() {
            return this.fullMatchingImages;
        }

        public final WebEntity[] getWebEntities() {
            return this.webEntities;
        }

        @Keep
        public static final class WebPage {
            private final WebImage[] fullMatchingImages;
            private final String pageTitle;
            private final WebImage[] partialMatchingImages;
            private final Float score;
            private final String url;

            public WebPage() {
                this(null, null, null, null, null, 31, null);
            }

            public WebPage(String str, WebImage[] webImageArr, Float f2, WebImage[] webImageArr2, String str2) {
                this.pageTitle = str;
                this.fullMatchingImages = webImageArr;
                this.score = f2;
                this.partialMatchingImages = webImageArr2;
                this.url = str2;
            }

            public /* synthetic */ WebPage(String str, WebImage[] webImageArr, Float f2, WebImage[] webImageArr2, String str2, int i, g gVar) {
                this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (WebImage[]) null : webImageArr, (i & 4) != 0 ? (Float) null : f2, (i & 8) != 0 ? (WebImage[]) null : webImageArr2, (i & 16) != 0 ? (String) null : str2);
            }

            public final String getPageTitle() {
                return this.pageTitle;
            }

            public final WebImage[] getFullMatchingImages() {
                return this.fullMatchingImages;
            }

            public final Float getScore() {
                return this.score;
            }

            public final WebImage[] getPartialMatchingImages() {
                return this.partialMatchingImages;
            }

            public final String getUrl() {
                return this.url;
            }

            @Keep
            public static final class WebImage {
                private final Float score;
                private final String url;

                /* JADX WARN: Multi-variable type inference failed */
                public WebImage() {
                    this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
                }

                public WebImage(Float f2, String str) {
                    this.score = f2;
                    this.url = str;
                }

                public /* synthetic */ WebImage(Float f2, String str, int i, g gVar) {
                    this((i & 1) != 0 ? (Float) null : f2, (i & 2) != 0 ? (String) null : str);
                }

                public final Float getScore() {
                    return this.score;
                }

                public final String getUrl() {
                    return this.url;
                }
            }
        }

        @Keep
        public static final class WebLabel {
            private final String label;
            private final String languageCode;

            /* JADX WARN: Multi-variable type inference failed */
            public WebLabel() {
                this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
            }

            public WebLabel(String str, String str2) {
                this.label = str;
                this.languageCode = str2;
            }

            public /* synthetic */ WebLabel(String str, String str2, int i, g gVar) {
                this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2);
            }

            public final String getLabel() {
                return this.label;
            }

            public final String getLanguageCode() {
                return this.languageCode;
            }
        }

        @Keep
        public static final class WebEntity {
            private final String description;
            private final String entityId;
            private final Float score;

            public WebEntity() {
                this(null, null, null, 7, null);
            }

            public WebEntity(String str, String str2, Float f2) {
                this.entityId = str;
                this.description = str2;
                this.score = f2;
            }

            public /* synthetic */ WebEntity(String str, String str2, Float f2, int i, g gVar) {
                this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (Float) null : f2);
            }

            public final String getEntityId() {
                return this.entityId;
            }

            public final String getDescription() {
                return this.description;
            }

            public final Float getScore() {
                return this.score;
            }
        }
    }

    @Keep
    public static final class CropHintsAnnotation {
        private final CropHint[] cropHints;

        /* JADX WARN: Multi-variable type inference failed */
        public CropHintsAnnotation() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public CropHintsAnnotation(CropHint[] cropHintArr) {
            this.cropHints = cropHintArr;
        }

        public /* synthetic */ CropHintsAnnotation(CropHint[] cropHintArr, int i, g gVar) {
            this((i & 1) != 0 ? (CropHint[]) null : cropHintArr);
        }

        public final CropHint[] getCropHints() {
            return this.cropHints;
        }

        @Keep
        public static final class CropHint {
            private final BoundingPoly boundingPoly;
            private final Float confidence;
            private final Float importanceFraction;

            public CropHint() {
                this(null, null, null, 7, null);
            }

            public CropHint(BoundingPoly boundingPoly, Float f2, Float f3) {
                this.boundingPoly = boundingPoly;
                this.confidence = f2;
                this.importanceFraction = f3;
            }

            public /* synthetic */ CropHint(BoundingPoly boundingPoly, Float f2, Float f3, int i, g gVar) {
                this((i & 1) != 0 ? (BoundingPoly) null : boundingPoly, (i & 2) != 0 ? (Float) null : f2, (i & 4) != 0 ? (Float) null : f3);
            }

            public final BoundingPoly getBoundingPoly() {
                return this.boundingPoly;
            }

            public final Float getConfidence() {
                return this.confidence;
            }

            public final Float getImportanceFraction() {
                return this.importanceFraction;
            }
        }
    }

    @Keep
    public static final class SafeSearchAnnotation {
        private final Likelihood adult;
        private final Likelihood medical;
        private final Likelihood racy;
        private final Likelihood spoof;
        private final Likelihood violence;

        public SafeSearchAnnotation() {
            this(null, null, null, null, null, 31, null);
        }

        public SafeSearchAnnotation(Likelihood likelihood, Likelihood likelihood2, Likelihood likelihood3, Likelihood likelihood4, Likelihood likelihood5) {
            this.adult = likelihood;
            this.spoof = likelihood2;
            this.medical = likelihood3;
            this.violence = likelihood4;
            this.racy = likelihood5;
        }

        public /* synthetic */ SafeSearchAnnotation(Likelihood likelihood, Likelihood likelihood2, Likelihood likelihood3, Likelihood likelihood4, Likelihood likelihood5, int i, g gVar) {
            this((i & 1) != 0 ? (Likelihood) null : likelihood, (i & 2) != 0 ? (Likelihood) null : likelihood2, (i & 4) != 0 ? (Likelihood) null : likelihood3, (i & 8) != 0 ? (Likelihood) null : likelihood4, (i & 16) != 0 ? (Likelihood) null : likelihood5);
        }

        public final Likelihood getAdult() {
            return this.adult;
        }

        public final Likelihood getSpoof() {
            return this.spoof;
        }

        public final Likelihood getMedical() {
            return this.medical;
        }

        public final Likelihood getViolence() {
            return this.violence;
        }

        public final Likelihood getRacy() {
            return this.racy;
        }
    }

    @Keep
    public static final class LocalizedObjectAnnotation {
        private final BoundingPoly boundingPoly;
        private final String languageCode;
        private final String mid;
        private final String name;
        private final Float score;

        public LocalizedObjectAnnotation() {
            this(null, null, null, null, null, 31, null);
        }

        public LocalizedObjectAnnotation(String str, String str2, String str3, Float f2, BoundingPoly boundingPoly) {
            this.mid = str;
            this.languageCode = str2;
            this.name = str3;
            this.score = f2;
            this.boundingPoly = boundingPoly;
        }

        public /* synthetic */ LocalizedObjectAnnotation(String str, String str2, String str3, Float f2, BoundingPoly boundingPoly, int i, g gVar) {
            this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (Float) null : f2, (i & 16) != 0 ? (BoundingPoly) null : boundingPoly);
        }

        public final String getMid() {
            return this.mid;
        }

        public final String getLanguageCode() {
            return this.languageCode;
        }

        public final String getName() {
            return this.name;
        }

        public final Float getScore() {
            return this.score;
        }

        public final BoundingPoly getBoundingPoly() {
            return this.boundingPoly;
        }
    }

    @Keep
    public static final class EntityAnnotation {
        private final String description;
        private final String locale;
        private final LocationInfo[] locations;
        private final String mid;
        private final Float score;

        public EntityAnnotation() {
            this(null, null, null, null, null, 31, null);
        }

        public EntityAnnotation(String str, String str2, String str3, Float f2, LocationInfo[] locationInfoArr) {
            this.mid = str;
            this.locale = str2;
            this.description = str3;
            this.score = f2;
            this.locations = locationInfoArr;
        }

        public /* synthetic */ EntityAnnotation(String str, String str2, String str3, Float f2, LocationInfo[] locationInfoArr, int i, g gVar) {
            this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (Float) null : f2, (i & 16) != 0 ? (LocationInfo[]) null : locationInfoArr);
        }

        public final String getMid() {
            return this.mid;
        }

        public final String getLocale() {
            return this.locale;
        }

        public final String getDescription() {
            return this.description;
        }

        public final Float getScore() {
            return this.score;
        }

        public final LocationInfo[] getLocations() {
            return this.locations;
        }

        @Keep
        public static final class LocationInfo {
            private final a latLng;

            /* JADX WARN: Multi-variable type inference failed */
            public LocationInfo() {
                this(null, 1, 0 == true ? 1 : 0);
            }

            public static final class a {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                private final String f7078a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private final String f7079b;

                /* JADX WARN: Multi-variable type inference failed */
                public a() {
                    this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
                }

                public a(String str, String str2) {
                    this.f7078a = str;
                    this.f7079b = str2;
                }

                public /* synthetic */ a(String str, String str2, int i, g gVar) {
                    this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2);
                }
            }

            public LocationInfo(a aVar) {
                this.latLng = aVar;
            }

            public /* synthetic */ LocationInfo(a aVar, int i, g gVar) {
                this((i & 1) != 0 ? (a) null : aVar);
            }

            public final a getLatLng() {
                return this.latLng;
            }
        }
    }

    @Keep
    public static final class ImageProperties {
        private final DominantColorsAnnotation dominantColors;

        /* JADX WARN: Multi-variable type inference failed */
        public ImageProperties() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public ImageProperties(DominantColorsAnnotation dominantColorsAnnotation) {
            this.dominantColors = dominantColorsAnnotation;
        }

        public /* synthetic */ ImageProperties(DominantColorsAnnotation dominantColorsAnnotation, int i, g gVar) {
            this((i & 1) != 0 ? (DominantColorsAnnotation) null : dominantColorsAnnotation);
        }

        public final DominantColorsAnnotation getDominantColors() {
            return this.dominantColors;
        }

        @Keep
        public static final class DominantColorsAnnotation {
            private final ColorInfo[] colors;

            /* JADX WARN: Multi-variable type inference failed */
            public DominantColorsAnnotation() {
                this(null, 1, 0 == true ? 1 : 0);
            }

            public DominantColorsAnnotation(ColorInfo[] colorInfoArr) {
                this.colors = colorInfoArr;
            }

            public /* synthetic */ DominantColorsAnnotation(ColorInfo[] colorInfoArr, int i, g gVar) {
                this((i & 1) != 0 ? (ColorInfo[]) null : colorInfoArr);
            }

            public final ColorInfo[] getColors() {
                return this.colors;
            }

            @Keep
            public static final class ColorInfo {
                private final Color color;
                private final Float pixelFraction;
                private final Float score;

                public ColorInfo() {
                    this(null, null, null, 7, null);
                }

                public ColorInfo(Color color, Float f2, Float f3) {
                    this.color = color;
                    this.score = f2;
                    this.pixelFraction = f3;
                }

                public /* synthetic */ ColorInfo(Color color, Float f2, Float f3, int i, g gVar) {
                    this((i & 1) != 0 ? (Color) null : color, (i & 2) != 0 ? (Float) null : f2, (i & 4) != 0 ? (Float) null : f3);
                }

                public final Color getColor() {
                    return this.color;
                }

                public final Float getScore() {
                    return this.score;
                }

                public final Float getPixelFraction() {
                    return this.pixelFraction;
                }

                @Keep
                public static final class Color {
                    private final Float alpha;
                    private final Float blue;
                    private final Float green;
                    private final Float red;

                    public Color() {
                        this(null, null, null, null, 15, null);
                    }

                    public Color(Float f2, Float f3, Float f4, Float f5) {
                        this.red = f2;
                        this.green = f3;
                        this.blue = f4;
                        this.alpha = f5;
                    }

                    public /* synthetic */ Color(Float f2, Float f3, Float f4, Float f5, int i, g gVar) {
                        this((i & 1) != 0 ? (Float) null : f2, (i & 2) != 0 ? (Float) null : f3, (i & 4) != 0 ? (Float) null : f4, (i & 8) != 0 ? (Float) null : f5);
                    }

                    public final Float getRed() {
                        return this.red;
                    }

                    public final Float getGreen() {
                        return this.green;
                    }

                    public final Float getBlue() {
                        return this.blue;
                    }

                    public final Float getAlpha() {
                        return this.alpha;
                    }
                }
            }
        }
    }

    @Keep
    public static final class FaceAnnotation {
        private final Likelihood angerLikelihood;
        private final Likelihood blurredLikelihood;
        private final BoundingPoly boundingPoly;
        private final Float detectionConfidence;
        private final BoundingPoly fdBoundingPoly;
        private final Likelihood headwearLikelihood;
        private final Likelihood joyLikelihood;
        private final Float landmarkingConfidence;
        private final Landmark[] landmarks;
        private final Float panAngle;
        private final Float rollAngle;
        private final Likelihood sorrowLikelihood;
        private final Likelihood surpriseLikelihood;
        private final Float tiltAngle;
        private final Likelihood underExposedLikelihood;

        public FaceAnnotation() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
        }

        public FaceAnnotation(BoundingPoly boundingPoly, BoundingPoly boundingPoly2, Landmark[] landmarkArr, Float f2, Float f3, Float f4, Float f5, Float f6, Likelihood likelihood, Likelihood likelihood2, Likelihood likelihood3, Likelihood likelihood4, Likelihood likelihood5, Likelihood likelihood6, Likelihood likelihood7) {
            this.boundingPoly = boundingPoly;
            this.fdBoundingPoly = boundingPoly2;
            this.landmarks = landmarkArr;
            this.rollAngle = f2;
            this.panAngle = f3;
            this.tiltAngle = f4;
            this.detectionConfidence = f5;
            this.landmarkingConfidence = f6;
            this.joyLikelihood = likelihood;
            this.sorrowLikelihood = likelihood2;
            this.angerLikelihood = likelihood3;
            this.surpriseLikelihood = likelihood4;
            this.underExposedLikelihood = likelihood5;
            this.blurredLikelihood = likelihood6;
            this.headwearLikelihood = likelihood7;
        }

        public /* synthetic */ FaceAnnotation(BoundingPoly boundingPoly, BoundingPoly boundingPoly2, Landmark[] landmarkArr, Float f2, Float f3, Float f4, Float f5, Float f6, Likelihood likelihood, Likelihood likelihood2, Likelihood likelihood3, Likelihood likelihood4, Likelihood likelihood5, Likelihood likelihood6, Likelihood likelihood7, int i, g gVar) {
            this((i & 1) != 0 ? (BoundingPoly) null : boundingPoly, (i & 2) != 0 ? (BoundingPoly) null : boundingPoly2, (i & 4) != 0 ? (Landmark[]) null : landmarkArr, (i & 8) != 0 ? (Float) null : f2, (i & 16) != 0 ? (Float) null : f3, (i & 32) != 0 ? (Float) null : f4, (i & 64) != 0 ? (Float) null : f5, (i & 128) != 0 ? (Float) null : f6, (i & 256) != 0 ? (Likelihood) null : likelihood, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (Likelihood) null : likelihood2, (i & 1024) != 0 ? (Likelihood) null : likelihood3, (i & 2048) != 0 ? (Likelihood) null : likelihood4, (i & NotificationCompat.FLAG_BUBBLE) != 0 ? (Likelihood) null : likelihood5, (i & 8192) != 0 ? (Likelihood) null : likelihood6, (i & 16384) != 0 ? (Likelihood) null : likelihood7);
        }

        public final BoundingPoly getBoundingPoly() {
            return this.boundingPoly;
        }

        public final BoundingPoly getFdBoundingPoly() {
            return this.fdBoundingPoly;
        }

        public final Landmark[] getLandmarks() {
            return this.landmarks;
        }

        public final Float getRollAngle() {
            return this.rollAngle;
        }

        public final Float getPanAngle() {
            return this.panAngle;
        }

        public final Float getTiltAngle() {
            return this.tiltAngle;
        }

        public final Float getDetectionConfidence() {
            return this.detectionConfidence;
        }

        public final Float getLandmarkingConfidence() {
            return this.landmarkingConfidence;
        }

        public final Likelihood getJoyLikelihood() {
            return this.joyLikelihood;
        }

        public final Likelihood getSorrowLikelihood() {
            return this.sorrowLikelihood;
        }

        public final Likelihood getAngerLikelihood() {
            return this.angerLikelihood;
        }

        public final Likelihood getSurpriseLikelihood() {
            return this.surpriseLikelihood;
        }

        public final Likelihood getUnderExposedLikelihood() {
            return this.underExposedLikelihood;
        }

        public final Likelihood getBlurredLikelihood() {
            return this.blurredLikelihood;
        }

        public final Likelihood getHeadwearLikelihood() {
            return this.headwearLikelihood;
        }

        @Keep
        public static final class Landmark {
            private final Position position;
            private final Type type;

            @Keep
            public enum Type {
                UNKNOWN_LANDMARK,
                LEFT_EYE,
                RIGHT_EYE,
                LEFT_OF_LEFT_EYEBROW,
                RIGHT_OF_LEFT_EYEBROW,
                LEFT_OF_RIGHT_EYEBROW,
                RIGHT_OF_RIGHT_EYEBROW,
                MIDPOINT_BETWEEN_EYES,
                NOSE_TIP,
                UPPER_LIP,
                LOWER_LIP,
                MOUTH_LEFT,
                MOUTH_RIGHT,
                MOUTH_CENTER,
                NOSE_BOTTOM_RIGHT,
                NOSE_BOTTOM_LEFT,
                NOSE_BOTTOM_CENTER,
                LEFT_EYE_TOP_BOUNDARY,
                LEFT_EYE_RIGHT_CORNER,
                LEFT_EYE_BOTTOM_BOUNDARY,
                LEFT_EYE_LEFT_CORNER,
                RIGHT_EYE_TOP_BOUNDARY,
                RIGHT_EYE_RIGHT_CORNER,
                RIGHT_EYE_BOTTOM_BOUNDARY,
                RIGHT_EYE_LEFT_CORNER,
                LEFT_EYEBROW_UPPER_MIDPOINT,
                RIGHT_EYEBROW_UPPER_MIDPOINT,
                LEFT_EAR_TRAGION,
                RIGHT_EAR_TRAGION,
                LEFT_EYE_PUPIL,
                RIGHT_EYE_PUPIL,
                FOREHEAD_GLABELLA,
                CHIN_GNATHION,
                CHIN_LEFT_GONION,
                CHIN_RIGHT_GONION
            }

            /* JADX WARN: Multi-variable type inference failed */
            public Landmark() {
                this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
            }

            public Landmark(Type type, Position position) {
                this.type = type;
                this.position = position;
            }

            public /* synthetic */ Landmark(Type type, Position position, int i, g gVar) {
                this((i & 1) != 0 ? (Type) null : type, (i & 2) != 0 ? (Position) null : position);
            }

            public final Type getType() {
                return this.type;
            }

            public final Position getPosition() {
                return this.position;
            }
        }
    }
}
