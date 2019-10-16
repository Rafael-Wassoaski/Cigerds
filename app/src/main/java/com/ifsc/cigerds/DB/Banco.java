package com.ifsc.cigerds.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import androidx.annotation.Nullable;

public class Banco extends SQLiteOpenHelper {

    public static class BancoEntry implements BaseColumns{

        private static final int VERSAO = 11;
        public static final String NOME_BANCO = "vistorias.db";
        private static final String TABELA = "vistoria";
        private static final String ID = "_id";
        private static final String IDCONTROLE = "idControle";
        private static final String AUTOR = "autor";
        private static final String COBRAD = "cobrad";
        private static final String MUNICIPIO = "municipio";


        private static final String DESCRICAO = "descricao";
        private static final String DATA = "data";
        private static final String ENDERECO = "endereco";

        private static final String LATITUDE = "latitude";
        private static final String LONGITUDE = "longitude";

        private static final String DANOS_HUMANOS_DESALOJADOS = "danos_humanos_desalojados";
        private static final String DANOS_HUMANOS_DESABRIGADOS = "danos_humanos_desabrigados";
        private static final String DANOS_HUMANOS_DESAPARECIDOS = "danos_humanos_desaparecidos";
        private static final String DANOS_HUMANOS_FERIDOS = "danos_humanos_feridos";
        private static final String DANOS_HUMANOS_ENFERMOS = "danos_humanos_enfermos";
        private static final String DANOS_HUMANOS_MORTOS = "danos_humanos_mortos";
        private static final String DANOS_HUMANOS_ISOLADOS = "danos_humanos_isolados";
        private static final String DANOS_HUMANOS_ATINGIDOS = "danos_humanos_atingidos";
        private static final String DANOS_HUMANOS_AFETADOS = "danos_humanos_afetados";
        private static final String DANOS_HUMANOS_OBSERVAÇÕES = "danos_humanos_observacoes";

        private static final String UNIDADES_HABITACIONAIS_ANTINGIDAS = "unidades_habitacionais_atingidas";
        private static final String UNIDADES_HABITACIONAIS_DANIFICADAS = "unidades_habitacionais_danificads";
        private static final String UNIDADES_HABITACIONAIS_INTERDITADAS = "unidades_habitacionais_interditadas";
        private static final String UNIDADES_HABITACIONAIS_DESTRUIDAS = "unidades_habitacionais_destruidas";
        private static final String INSTALACOES_PUBLICAS_SAUDE_ATINGIDAS = "instalacoes_publicas_saude_atingidas";
        private static final String INSTALACOES_PUBLICAS_ENSINO_ATINGIDAS = "instalacoes_publicas_ensino_atingidas";
        private static final String INSTALACOES_COMUNITARIAS_ATINGIDAS = "instalacoes_comunitarias_atingidas";
        private static final String OBRAS_ATINGIDAS = "obras_atingidas";
        private static final String INTERRUPCOES_SERVICOS_ESSENCIAIS = "interrupcoes_servicos_essenciais";
        private static final String DANOS_MATERIAIS_OBSERVACOES = "danos_materiais_observacoes";

        private static final String CONTAMINACAO_SOLO = "contaminacao_solo";
        private static final String CONTAMINACAO_AGUA = "contaminacao_agua";
        private static final String CONTAMINACAO_AR = "contaminacao_ar";
        private static final String DANOS_AMBIENTAIS_OBSERVACOES = "danos_ambientais_observacoes";

        private static final String DANOS_AGRICULTURA = "danos_agricultura";
        private static final String DANOS_PECUARIA = "danos_pecuaria";
        private static final String DANOS_INDUSTRIA = "danos_industria";
        private static final String DANOS_COMERCIO = "danos_comercio";
        private static final String DANOS_PRESTACAO_DE_SERVICOS = "danos_prestacao_de_servicos";
        private static final String DANOS_ECONOMICOS_OBSERVACOES = "danos_economicos_observacoes";


        private static final String IAH_CESTAS_DE_ALIMENTOS = "iah_cestas_de_alimentos";
        private static final String IAH_AGUA_POTAVEL = "iah_agua_potavel";
        private static final String IAH_COLCHOES = "iah_colchoes";
        private static final String IAH_KIT_HIGIENE_PESSOAL = "iah_kit_higiene_pessoal";
        private static final String IAH_KIT_LIMPEZA = "iah_kit_limpeza";
        private static final String IAH_TELHAS = "iah_telhas";
        private static final String IAH_LONA_PLASTICA = "iah_lona_plastica";
        private static final String IAH_OUTROS = "iah_outros";
        private static final String IAH_FONECIDOS_OUTROS_OBSERVACOES = "iah_fornecidos_outros_observacoes";
        private static final String IAH_VIAS_PUBLICAS_TOTALMENTE_DESOBISTRUIDAS = "iah_vias_publicas_totalmente_desobistruidas";
        private static final String IAH_REESTABELECIMENTO_SERVICOS_ESSENCIAIS = "iah_reestabelecimento_servicos_essenciais";


    }

    private String sqlCreateTable ="CREATE TABLE " + BancoEntry.TABELA +"(" +
            BancoEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ BancoEntry.COBRAD+" TEXT, "+BancoEntry.MUNICIPIO+" TEXT, "+BancoEntry.DESCRICAO+" TEXT, "+BancoEntry.DATA+" TEXT, "+BancoEntry.ENDERECO+" TEXT, "+BancoEntry.DANOS_HUMANOS_DESALOJADOS+" TEXT, "+BancoEntry.DANOS_HUMANOS_DESABRIGADOS+" TEXT, "+ BancoEntry.DANOS_HUMANOS_DESAPARECIDOS + " TEXT, "+BancoEntry.DANOS_HUMANOS_FERIDOS+ " TEXT, "+BancoEntry.DANOS_HUMANOS_ENFERMOS + " TEXT, " + BancoEntry.DANOS_HUMANOS_MORTOS + " TEXT, "+BancoEntry.DANOS_HUMANOS_ISOLADOS+" TEXT, "+BancoEntry.DANOS_HUMANOS_ATINGIDOS +" TEXT, "+ BancoEntry.DANOS_HUMANOS_AFETADOS +" TEXT, " + BancoEntry.DANOS_HUMANOS_OBSERVAÇÕES+" TEXT, "+
            BancoEntry.UNIDADES_HABITACIONAIS_ANTINGIDAS+" TEXT, "+BancoEntry.UNIDADES_HABITACIONAIS_DANIFICADAS+" TEXT, " + BancoEntry.UNIDADES_HABITACIONAIS_INTERDITADAS+" TEXT, "+ BancoEntry.UNIDADES_HABITACIONAIS_DESTRUIDAS+" TEXT, "+ BancoEntry.INSTALACOES_PUBLICAS_SAUDE_ATINGIDAS +" TEXT, "+BancoEntry.INSTALACOES_PUBLICAS_ENSINO_ATINGIDAS+" TEXT, "+BancoEntry.INSTALACOES_COMUNITARIAS_ATINGIDAS+" TEXT, "+BancoEntry.OBRAS_ATINGIDAS+" TEXT, "+BancoEntry.INTERRUPCOES_SERVICOS_ESSENCIAIS+" TEXT, "+BancoEntry.DANOS_MATERIAIS_OBSERVACOES+" TEXT, "+
            BancoEntry.CONTAMINACAO_SOLO+" TEXT, "+BancoEntry.CONTAMINACAO_AGUA+" TEXT, "+BancoEntry.CONTAMINACAO_AR+" TEXT, "+BancoEntry.DANOS_AMBIENTAIS_OBSERVACOES+" TEXT, "+
            BancoEntry.DANOS_AGRICULTURA+" TEXT, "+BancoEntry.DANOS_PECUARIA+" TEXT, "+BancoEntry.DANOS_INDUSTRIA+" TEXT, "+BancoEntry.DANOS_COMERCIO+" TEXT, "+BancoEntry.DANOS_PRESTACAO_DE_SERVICOS+" TEXT, "+BancoEntry.DANOS_ECONOMICOS_OBSERVACOES+" TEXT, "+
            BancoEntry.IAH_CESTAS_DE_ALIMENTOS+" TEXT, "+BancoEntry.IAH_AGUA_POTAVEL+" TEXT, "+BancoEntry.IAH_COLCHOES+" TEXT, "+BancoEntry.IAH_KIT_HIGIENE_PESSOAL+" TEXT, "+BancoEntry.IAH_KIT_LIMPEZA+" TEXT, "+BancoEntry.IAH_TELHAS+" TEXT, "+BancoEntry.IAH_LONA_PLASTICA+" TEXT, "+BancoEntry.IAH_OUTROS+" TEXT, "+BancoEntry.IAH_FONECIDOS_OUTROS_OBSERVACOES+" TEXT, "+BancoEntry.IAH_VIAS_PUBLICAS_TOTALMENTE_DESOBISTRUIDAS+" TEXT, "+BancoEntry.IAH_REESTABELECIMENTO_SERVICOS_ESSENCIAIS+" TEXT ,"+
            BancoEntry.LATITUDE +" TEXT,  "+BancoEntry.LONGITUDE+" TEXT "+
                ")";

    private static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " + BancoEntry.TABELA;


    public Banco(@Nullable Context context) {
        super(context, BancoEntry.NOME_BANCO, null, BancoEntry.VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
