CREATE TABLE warehouselotstack (
    id uuid,
    lotid uuid,
    stackid uuid,
    bags int,
    ondate timestamp,
    byuser text,
    PRIMARY KEY (id, lotid, stackid)
)