package fr.ufc.l3info.oprog;

public class FabriqueVelo {
    private static FabriqueVelo INSTANCE = null;

    private FabriqueVelo() {}

    public static FabriqueVelo getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new FabriqueVelo();
        }
        return INSTANCE;
    }


    public IVelo construire (char t, String... options) {
        IVelo v = new Velo(t);
        int s = options.length;
        if(s == 0 || options == null)
            return v;
        int cadre_alu = 0, suspension_avant = 0, suspension_arriere = 0, assistance_elec = 0, freins_disque = 0;

        for (int i = 0; i < s; i++) {
            if(options[i] == null) {
                continue;
            }
            if(options[i].equals("CADRE_ALUMINIUM") && cadre_alu == 0) {
                v = new OptCadreAlu(v);
                cadre_alu = 1;
            }
            if(options[i].equals("SUSPENSION_AVANT") && suspension_avant == 0) {
                v = new OptSuspensionAvant(v);
                suspension_avant = 1;
            }
            if(options[i].equals("SUSPENSION_ARRIERE") && suspension_arriere == 0) {
                v = new OptSuspensionArriere(v);
                suspension_arriere = 1;
            }
            if(options[i].equals("ASSISTANCE_ELECTRIQUE") && assistance_elec == 0) {
                v = new OptAssistanceElectrique(v);
                assistance_elec = 1;
            }
            if(options[i].equals("FREINS_DISQUE") && freins_disque == 0){
                v = new OptFreinsDisque(v);
                freins_disque = 1;
            }

        }
        return v;
    }
}
