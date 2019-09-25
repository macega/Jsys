; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!
#define MyAppName="Jsys"

; #define MyAppVersion GetFileVersion('dist\Jsys-sql.jar')
; #define ApplicationVersion GetFileVersion('dist\Jsys-sql.jar')

#define MyAppVersion="62.3.4006.15"
#define ApplicationVersion="62.3.4006.15"
#define MyAppPublisher="Jsys"
#define MyAppExeName="Jsys.jar"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{5AC01B77-DD89-4C80-BBD1-D2BF8FA32231}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
VersionInfoVersion={#ApplicationVersion}
AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
DefaultDirName=C:\Jsys\app\
; DisableDirPage=yes
DefaultGroupName={#MyAppName}
DisableProgramGroupPage=true

OutputDir=F:\Google Drive\Jsys\{#ApplicationVersion}
; OutputDir=\\server-ibm\SUPORTE

; OutputBaseFilename=JsysSetup {#ApplicationVersion}
OutputBaseFilename=JsysSetup
Compression=lzma/ultra
SolidCompression=true
InternalCompressLevel=ultra

[Languages]
Name: brazilianportuguese; MessagesFile: compiler:Languages\BrazilianPortuguese.isl

[Tasks]
Name: desktopicon; Description: {cm:CreateDesktopIcon}; GroupDescription: {cm:AdditionalIcons}
Name: quicklaunchicon; Description: {cm:CreateQuickLaunchIcon}; GroupDescription: {cm:AdditionalIcons}; OnlyBelowVersion: 0,6.1

[InstallDelete]
Name: {app}\scripts\*; Type: filesandordirs; Tasks: ; Languages: 
Name: {app}\jsys-sql.jar; Type: filesandordirs; Tasks: ; Languages: 
Name: {app}\AdaptaJsys.jar; Type: filesandordirs; Tasks: ; Languages: 
Name: {app}\*.csv; Type: filesandordirs; Tasks: ; Languages: 
Name: {app}\lib\*; Type: filesandordirs; Tasks: ; Languages: 
Name: {app}\xsd\*; Type: filesandordirs; Tasks: ; Languages: 
Name: {app}\mod\Jsys.ini; Type: dirifempty; Tasks: ; Languages: 

[Files]
Source: dist\Jsys.jar; DestDir: {app}; Flags: ignoreversion
Source: Jsys.ini; DestDir: {app}\mod; Flags: ignoreversion
Source: updates.txt; DestDir: {app}; Flags: ignoreversion
Source: log4j.properties; DestDir: {app}; Flags: ignoreversion
Source: dist\lib\*; DestDir: {app}\lib; Flags: ignoreversion recursesubdirs createallsubdirs
; Source: xsd\*; DestDir: {app}\xsd; Flags: ignoreversion recursesubdirs createallsubdirs
Source: Schemas 4.00\*; DestDir: {app}\Schemas 4.00; Flags: ignoreversion recursesubdirs createallsubdirs
Source: *.csv; DestDir: {app}; Flags: ignoreversion
Source: scripts\*; DestDir: {app}\scripts; Flags: ignoreversion recursesubdirs createallsubdirs
Source: imports\*; DestDir: {app}\scripts; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files
Source: Jsys.ico; DestDir: {app}

; Arquivos AdaptaJsys
; Source: C:\Fontes\AdaptaJsys\dist\AdaptaJsys.jar; DestDir: {app}; Flags: ignoreversion
; Source: C:\Fontes\AdaptaJsys\scripts\*; DestDir: {app}\scripts; Flags: ignoreversion recursesubdirs createallsubdirs
; Source: C:\Fontes\AdaptaJsys\imports\*; DestDir: {app}\scripts; Flags: ignoreversion recursesubdirs createallsubdirs
; Source: C:\Fontes\AdaptaJsys\jsysContas0.001.csv; DestDir: {app}; Flags: ignoreversion
; Source: C:\Fontes\AdaptaJsys\jsysSubConta0.001.csv; DestDir: {app}; Flags: ignoreversion
; Source: C:\Fontes\AdaptaJsys\TabelaIBPTaxRO16.2.A.csv; DestDir: {app}; Flags: ignoreversion
; Source: C:\Fontes\AdaptaJsys\TabelaCidades0.001.csv; DestDir: {app}; Flags: ignoreversion
; Arquivos ECF
; Source: C:\Fontes\ECF\dll\*; DestDir: {app}\ecf; Flags: ignoreversion recursesubdirs createallsubdirs
; Source: C:\Fontes\ECF\dist\ECF.jar; DestDir: {app}\ecf; Flags: ignoreversion
; Source: C:\Fontes\ECF\print.gif; DestDir: {app}\ecf; Flags: ignoreversion
; Source: C:\Fontes\ECF\dist\lib\*; DestDir: {app}\ecf\lib; Flags: ignoreversion
; Source: C:\Fontes\ECF\dist\README.TXT; DestDir: {app}\ecf\log; Flags: ignoreversion
; Fim Arquivos ECF

[Icons]
Name: {group}\{#MyAppName}; Filename: {app}\{#MyAppExeName}; IconFilename: {app}\Jsys.ico; IconIndex: 0
Name: {group}\{cm:UninstallProgram,{#MyAppName}}; Filename: {uninstallexe}
Name: {commondesktop}\{#MyAppName}; Filename: {app}\{#MyAppExeName}; Tasks: desktopicon; IconFilename: {app}\Jsys.ico; IconIndex: 0
Name: {userappdata}\Microsoft\Internet Explorer\Quick Launch\{#MyAppName}; Filename: {app}\{#MyAppExeName}; Tasks: quicklaunchicon; IconFilename: {app}\Jsys.ico; IconIndex: 0

[Run]
Filename: {app}\{#MyAppExeName}; Description: {cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}; Flags: shellexec postinstall skipifsilent
; Filename: {app}\jsys.jar adapta; Description: Adaptar o Banco de dados; Flags: shellexec postinstall skipifsilent unchecked
