package com.app.fragments.ui.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fragments.R;
import com.app.fragments.ui.components.XgpManejoMelhoramentoComponent;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class XgpManejoMelhoramentoAdapter extends RecyclerView.Adapter<XgpManejoMelhoramentoAdapter.ViewHolder> {

    private final Context context;
    private final List<XgpManejoMelhoramentoComponent> components;
    private ObservationValidator observationValidator;

    public XgpManejoMelhoramentoAdapter(Context context, List<XgpManejoMelhoramentoComponent> components) {
        this.context = context;
        this.components = components;
    }

    /**
     * Define o validador de observações
     */
    public void setObservationValidator(ObservationValidator validator) {
        this.observationValidator = validator;
        // Notifica o adapter para re-validar todos os itens
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_xgp_manejo_melhoramento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(components.get(position));
    }

    @Override
    public int getItemCount() {
        return components.size();
    }

    /**
     * Interface para validação de observações
     */
    public interface ObservationValidator {
        boolean isValidObservation(String value);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtName;
        private final TextView txtAbbreviation;
        private final TextInputEditText inputValue;
        private final TextInputLayout inputContainer;
        private final Context context;

        private XgpManejoMelhoramentoComponent currentComponent;
        private TextWatcher currentWatcher;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            txtName = itemView.findViewById(R.id.nome_caracteristica);
            txtAbbreviation = itemView.findViewById(R.id.sigla_caracteristica);
            inputValue = itemView.findViewById(R.id.valor);
            inputContainer = itemView.findViewById(R.id.container);
        }

        void bind(XgpManejoMelhoramentoComponent component) {
            currentComponent = component;

            setComponentTexts(component);
            configureInput(component);
        }

        private void setComponentTexts(XgpManejoMelhoramentoComponent component) {
            txtName.setText(component.getCaracteristica());
            txtAbbreviation.setText(component.getSigla());
        }

        private void configureInput(XgpManejoMelhoramentoComponent component) {
            clearPreviousWatcher();
            setInputValue(component);
            setupInputByType(component);
            resetInputStyle();
        }

        private void clearPreviousWatcher() {
            if (currentWatcher != null) {
                inputValue.removeTextChangedListener(currentWatcher);
                currentWatcher = null;
            }
        }

        private void setInputValue(XgpManejoMelhoramentoComponent component) {
            String value = component.getValorDigitado();
            inputValue.setText(value != null ? value : "");
        }

        private void setupInputByType(XgpManejoMelhoramentoComponent component) {
            if (isObservationComponent(component)) {
                configureObservationInput(component);
            } else {
                String type = component.getTipo();
                char typeChar = getTypeChar(type);

                switch (typeChar) {
                    case 'D':
                        configureDoubleInput(component);
                        break;
                    case 'I':
                        configureIntegerInput(component);
                        break;
                    case 'T':
                    default:
                        configureTextInput(component);
                        break;
                }
            }
        }

        /**
         * Verifica se o componente é uma observação
         */
        private boolean isObservationComponent(XgpManejoMelhoramentoComponent component) {
            return "S".equalsIgnoreCase(component.getEhObservacao());
        }

        private char getTypeChar(String type) {
            return (type != null && !type.isEmpty()) ? type.toUpperCase().charAt(0) : 'T';
        }

        /**
         * Configura input para observações
         */
        private void configureObservationInput(XgpManejoMelhoramentoComponent component) {
            setInputTypeAndHint(InputType.TYPE_CLASS_TEXT, "Digite a observação");
            addObservationValidationWatcher(component);
        }

        private void configureDoubleInput(XgpManejoMelhoramentoComponent component) {
            setInputTypeAndHint(InputType.TYPE_NUMBER_FLAG_DECIMAL, "Digite valor decimal");
            addDoubleValidationWatcher(component);
        }

        private void configureIntegerInput(XgpManejoMelhoramentoComponent component) {
            setInputTypeAndHint(InputType.TYPE_CLASS_NUMBER, "Digite nota");
            addIntegerValidationWatcher(component);
        }

        private void configureTextInput(XgpManejoMelhoramentoComponent component) {
            setInputTypeAndHint(InputType.TYPE_CLASS_TEXT, "Digite valor");
            addTextWatcher(component);
        }

        private void setInputTypeAndHint(int inputType, String hint) {
            inputValue.setInputType(inputType);
            inputContainer.setHint(hint);
        }

        /**
         * Adiciona validação específica para observações
         */
        private void addObservationValidationWatcher(XgpManejoMelhoramentoComponent component) {
            currentWatcher = createTextWatcher(component, this::validateObservationInput);
            inputValue.addTextChangedListener(currentWatcher);
        }

        private void addDoubleValidationWatcher(XgpManejoMelhoramentoComponent component) {
            currentWatcher = createTextWatcher(component, this::validateDoubleInput);
            inputValue.addTextChangedListener(currentWatcher);
        }

        private void addIntegerValidationWatcher(XgpManejoMelhoramentoComponent component) {
            currentWatcher = createTextWatcher(component, this::validateIntegerInput);
            inputValue.addTextChangedListener(currentWatcher);
        }

        private void addTextWatcher(XgpManejoMelhoramentoComponent component) {
            currentWatcher = createTextWatcher(component, (comp, value) -> resetInputStyle());
            inputValue.addTextChangedListener(currentWatcher);
        }

        private TextWatcher createTextWatcher(XgpManejoMelhoramentoComponent component, ValidationCallback callback) {
            return new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String value = s.toString();
                    component.setValorDigitado(value);
                    callback.validate(component, value);
                }

                @Override
                public void afterTextChanged(Editable s) {}
            };
        }

        /**
         * Valida entrada de observação
         */
        private void validateObservationInput(XgpManejoMelhoramentoComponent component, String value) {
            if (isEmptyValue(value)) {
                resetInputStyle();
                return;
            }

            if (observationValidator != null) {
                if (observationValidator.isValidObservation(value)) {
                    showValid();
                } else {
                    showError("Observação inválida. Verifique se contém uma descrição válida.");
                }
            } else {
                resetInputStyle();
            }
        }

        private void validateDoubleInput(XgpManejoMelhoramentoComponent component, String value) {
            if (isEmptyValue(value)) return;

            try {
                double numericValue = Double.parseDouble(value);
                showValid();
            } catch (NumberFormatException e) {
                showError("Apenas números decimais são permitidos");
            }
        }

        private void validateIntegerInput(XgpManejoMelhoramentoComponent component, String value) {
            if (isEmptyValue(value)) return;

            try {
                int numericValue = Integer.parseInt(value);
                validateRange(numericValue, component.getNotaInicial(), component.getNotaFinal());
            } catch (NumberFormatException e) {
                showError("Apenas números inteiros são permitidos");
            }
        }

        private boolean isEmptyValue(String value) {
            if (value.isEmpty()) {
                resetInputStyle();
                return true;
            }
            return false;
        }

        private void validateRange(double value, Integer min, Integer max) {
            if (min == null || max == null) {
                showValid();
                return;
            }

            if (value >= min && value <= max) {
                showValid();
            } else {
                showError(String.format("Deve estar entre %s e %s", min, max));
            }
        }

        private void showValid() {
            setInputStyle(R.color.colorValid, null);
        }

        private void showError(String message) {
            setInputStyle(R.color.colorError, message);
        }

        private void resetInputStyle() {
            setInputStyle(R.color.colorPrimary, null);
        }

        private void setInputStyle(int colorRes, String errorMessage) {
            inputContainer.setBoxStrokeColor(ContextCompat.getColor(context, colorRes));
            inputContainer.setError(errorMessage);
        }

        @FunctionalInterface
        private interface ValidationCallback {
            void validate(XgpManejoMelhoramentoComponent component, String value);
        }
    }
}