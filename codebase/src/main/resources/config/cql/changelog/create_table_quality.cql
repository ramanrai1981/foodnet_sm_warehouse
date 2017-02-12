CREATE TABLE warehousecommodityquality (
    lotid uuid,
    qualityparam text,
    ondate timestamp,
    qualityvalue text,
    byuser text,
    PRIMARY KEY (lotid, qualityparam)
)