FROM postgres:16
ENV POSTGRES_PASSWORD=!omnisac
ENV POSTGRES_USER=omnisac
ENV POSTGRES_DB=omnisac

COPY pg_hba.conf /etc/postgresql/postgresql.conf.d

EXPOSE 5432

CMD ["postgres"]